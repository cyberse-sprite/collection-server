package cc.lupss.doujin.doujinserver.util;

import cc.lupss.doujin.doujinserver.entity.Comic;
import cc.lupss.doujin.doujinserver.entity.Tag;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SaveFile {
    public static SaveFileInfo getInfoName(Comic comic) {
        Map<String, List<String>> map = new HashMap<>();
        List<Tag> tags = comic.getTags();
        if (tags != null) {
            for (Tag tag : tags) {
                List<String> addTags;
                if (map.containsKey(tag.getType())) {
                    addTags = map.get(tag.getType());
                } else {
                    addTags = new ArrayList<>();
                }
                addTags.add(tag.getValue());
                map.put(tag.getType(), addTags);
            }
        }
        String creator = "";
        if (map.containsKey("group")) {
            List<String> groups = map.get("group");
            if (groups.size() > 0) {
                creator += StringUtils.join(groups, ',');
            }
        }
        if (map.containsKey("author")) {
            List<String> author = map.get("author");
            if (author.size() > 0) {
                if (!creator.equals("")) {
                    creator += String.format("(%s)", StringUtils.join(author, ','));
                } else {
                    creator += StringUtils.join(author, ',');
                }
            }
        }
        String infoName = "";
        if (map.containsKey("activity")) {
            infoName += String.format("(%s)", StringUtils.join(map.get("activity"), ','));
        }
        if (!creator.equals("")) {
            infoName += String.format("[%s]", creator);
        }
        infoName += comic.getTitle();
        if (map.containsKey("origin")) {
            infoName += String.format("(%s)", StringUtils.join(map.get("origin"), ','));
        }
        if (!Objects.equals(comic.getLanguage(), "") && comic.getLanguage() != null) {
            infoName += String.format("[%s]", comic.getLanguage());
        }
        if (map.containsKey("charactor")) {
            infoName += String.format("(%s)", StringUtils.join(map.get("charactor"), ','));
        }
        if (map.containsKey("relationship")) {
            infoName += String.format("(%s)", StringUtils.join(map.get("relationship"), ','));
        }
        SaveFileInfo info = new SaveFileInfo();
        info.setInfoName(filenameFilter(infoName));
        if (map.containsKey("author")) {
            info.setFolder(map.get("author").get(0));
        } else {
            info.setFolder("noAuthor");
        }
        return info;
    }

    private static Pattern FilePattern = Pattern.compile("[\\\\/:*?\"<>|]");

    public static String filenameFilter(String str) {
        return str == null ? null : FilePattern.matcher(str).replaceAll("_");
    }

    public static String[] mkZip(MultipartFile[] targetFiles, String tempPath, String resPath, SaveFileInfo info) throws IOException {
        List<File> files = new ArrayList<>();
        int i = 0;
        for (MultipartFile m : targetFiles) {
            File file = new File(tempPath, String.format("%03d", i) + "." + FilenameUtils.getExtension(m.getOriginalFilename()));
            file.setWritable(true, false);
            m.transferTo(file);
            files.add(file);
            i++;
        }
        File zip = new File(String.format("%s/comic/%s/%s.zip", resPath, info.getFolder(), info.getInfoName()));
        File image = new File(String.format("%s/comic/%s/%s.%s", resPath, info.getFolder(),
                info.getInfoName(), FilenameUtils.getExtension(targetFiles[0].getOriginalFilename())));

        FileUtils.forceMkdirParent(zip);
        zip.setWritable(true, false);
        zip.createNewFile();
        String fileFolders = zipFileFolders(files, zip);
        FileUtils.copyFile(files.get(0), image);
        ImageUtil.storeThumbnailWithImage(image.getAbsolutePath(),image.getAbsolutePath());

        File directory = new File(tempPath);
        FileUtils.cleanDirectory(directory);

        return new String[]{replaceRes(resPath, fileFolders),
                replaceRes(resPath, FilenameUtils.removeExtension(fileFolders)
                        + "." + FilenameUtils.getExtension(files.get(0).getName()))};
    }

    /**
     * 功能:压缩多个文件，文件夹成一个zip文件
     *
     * @param srcfile：源文件列表
     * @param zipfile：压缩后的文件
     */
    public static String zipFileFolders(List<File> srcfile, File zipfile) {
        byte[] buf = new byte[1024];
        ZipOutputStream out = null;
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.size(); i++) {
                FileInputStream in = new FileInputStream(srcfile.get(i));
                String filePath = "";
                if (filePath == null)
                    filePath = "";
                else
                    filePath += "/";
                out.putNextEntry(new ZipEntry(filePath + srcfile.get(i).getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            return zipfile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static SaveFileInfo saveImagesAsZip(Comic comic, MultipartFile[] files, String tempPath, String resPath) {
        SaveFileInfo info = getInfoName(comic);
        if (!Objects.equals(info.getInfoName(), "")) {
            try {
                String[] path = mkZip(files, tempPath, resPath, info);
                info.setCode(0);
                info.setMessage("打包文件成功");
                info.setZipPath(path[0]);
                info.setImagePath(path[1]);
            } catch (IOException e) {
                info.setCode(2);
                info.setMessage("打包文件失败");
                throw new RuntimeException(e);
            }
        } else {
            info.setCode(1);
            info.setMessage("没有生成信息名称");
        }
        return info;
    }

    public static String replaceRes(String resPath, String name) {
        return name.replace("\\", "/").replace(resPath, "");
    }
}
