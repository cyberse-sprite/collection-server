package cc.lupss.doujin.doujinserver.controller;

import cc.lupss.doujin.doujinserver.controller.vo.Comic;
import cc.lupss.doujin.doujinserver.dal.database.ContentDO;
import cc.lupss.doujin.doujinserver.dal.mapper.ContentMapper;
import cc.lupss.doujin.doujinserver.service.ContentService;
import cc.lupss.doujin.doujinserver.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ComicController {
    @Resource
    ContentService contentService;
    @Resource
    ContentMapper contentMapper;
    @Resource
    @Value("${res-path}")
    private String resPath;
    @Value("${temp-path}")
    private String tempPath;

    @GetMapping("/comics")
    public Result getComics(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "0") Integer tag) {
        Page<Comic> page = new Page(current, 16);
        List<String[]> orders = new ArrayList<>() {{
            add(new String[]{"orders", "true"});
            add(new String[]{"create_time", "false"});
            add(new String[]{"title", "true"});
            add(new String[]{"publish_time", "true"});
            add(new String[]{"update_time", "true"});
        }};
        return Result.ok().data("list", contentMapper.selectComicsPage(page, null));
    }

    @GetMapping("/comic/upload")
    public Result ComicUploadInfo() {
//        List<Tag> tags = tagService.list();
//        Map<String, List<Tag>> map = new HashMap<>();
//        for (Tag one : tags) {
//            String tagType = one.getType();
//            if (map.containsKey(tagType)) {
//                List<Tag> list = map.get(tagType);
//                list.add(one);
//                map.put(tagType, list);
//            } else {
//                List<Tag> list = new ArrayList<>();
//                list.add(one);
//                map.put(tagType, list);
//            }
//        }
        return Result.ok().data("selections", "");
    }

    @GetMapping("/comic")
    public Result getComic(Integer id) {
        return Result.ok().data("comic", contentService.getById(id));
    }

    @GetMapping(value = "/comic/thumb", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] ComicThumb(String id) throws Exception {
//        ContentDO contentDO = contentService.getById(id);
//        File file = new File(resPath + contentDO.getImage());
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
        return (new byte[10]);
    }

    @GetMapping("/comic/res")
    @ResponseBody
    public void viewOrigin(int id, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ContentDO contentDO = contentService.getById(id);
//        String path = resPath + contentDO.getFilePath();
//        File file = new File(path);
//        String filename = FilenameUtils.getName(contentDO.getFilePath());
//        response.setHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
//        InputStream in = new FileInputStream(file);
//        byte[] buf = new byte[1024];
//        while (in.read(buf) > 0) {
//            response.getOutputStream().write(buf, 0, buf.length);
//        }
//        in.close();
    }

    @PostMapping("/comic")
    public Result postComic(@RequestPart ContentDO contentDO, @RequestPart MultipartFile[] files) {
//        SaveFileInfo info = SaveFile.saveImagesAsZip(contentDO, files, tempPath, resPath);
//        if (info.getCode() == 0) {
////            contentDO.setFilePath(info.getZipPath());
//            contentDO.setImage(info.getImagePath());
//            contentService.saveCustom(contentDO);
//            return Result.ok();
//        } else {
//            return Result.error();
//        }
        return Result.ok();
    }

    @PostMapping("/comic/own")
    public Result setComicOwn(Integer id, boolean own) {
        ContentDO contentDO = contentService.getById(id);
//        contentDO.setOwn(own);
        contentService.updateById(contentDO);
        return Result.ok();
    }
}
