package cc.lupss.doujin.doujinserver.controller;

import cc.lupss.doujin.doujinserver.entity.Comic;
import cc.lupss.doujin.doujinserver.entity.Tag;
import cc.lupss.doujin.doujinserver.service.ComicService;
import cc.lupss.doujin.doujinserver.service.TagService;
import cc.lupss.doujin.doujinserver.util.ImageUtil;
import cc.lupss.doujin.doujinserver.util.Result;
import cc.lupss.doujin.doujinserver.util.SaveFile;
import cc.lupss.doujin.doujinserver.util.SaveFileInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ComicController {
    @Resource
    ComicService comicService;
    @Resource
    TagService tagService;
    @Value("${res-path}")
    private String resPath;
    @Value("${temp-path}")
    private String tempPath;

    @GetMapping("/comic/upload")
    public Result ComicUploadInfo() {
        List<Tag> tags = tagService.list();
        Map<String, List<Tag>> map = new HashMap<>();
        for (Tag one : tags) {
            String tagType = one.getType();
            if (map.containsKey(tagType)) {
                List<Tag> list = map.get(tagType);
                list.add(one);
                map.put(tagType, list);
            } else {
                List<Tag> list = new ArrayList<>();
                list.add(one);
                map.put(tagType, list);
            }
        }
        return Result.ok().data("selections", map);
    }

    @GetMapping("/comic")
    public Result getComic(Integer id) {
        return Result.ok().data("comic", comicService.getByIdWithTags(id));
    }

    @GetMapping(value = "/comic/thumb", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] ComicThumb(String id) throws Exception {
        Comic comic = comicService.getById(id);
        File file = new File(resPath + comic.getImage());
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    @GetMapping("/comic/res")
    @ResponseBody
    public void viewOrigin(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Comic comic = comicService.getById(id);
        String path = resPath + comic.getFilePath();
        File file = new File(path);
        String filename = FilenameUtils.getName(comic.getFilePath());
        response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        InputStream in = new FileInputStream(file);
        byte[] buf = new byte[1024];
        while (in.read(buf) > 0) {
            response.getOutputStream().write(buf, 0, buf.length);
        }
        in.close();
    }

    @PostMapping("/comic")
    public Result postComic(@RequestPart Comic comic, @RequestPart MultipartFile[] files) {
        SaveFileInfo info = SaveFile.saveImagesAsZip(comic, files, tempPath, resPath);
        if (info.getCode() == 0) {
            comic.setFilePath(info.getZipPath());
            comic.setImage(info.getImagePath());
            comicService.saveCustom(comic);
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @GetMapping("/test")
    public Result test() {
        List<Comic> comics = comicService.list();
        for (Comic comic : comics) {
            ImageUtil.storeThumbnailWithImage(resPath + comic.getImage(), resPath + comic.getImage());
        }
        return Result.ok();
    }

    @PostMapping("/comic/own")
    public Result setComicOwn(Integer id, boolean own) {
        Comic comic = comicService.getById(id);
        comic.setOwn(own);
        comicService.updateById(comic);
        return Result.ok();
    }
}
