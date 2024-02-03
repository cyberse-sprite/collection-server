package cc.lupss.doujin.controller;

import cc.lupss.doujin.controller.vo.Comic;
import cc.lupss.doujin.dal.database.ContentDO;
import cc.lupss.doujin.dal.mapper.ContentMapper;
import cc.lupss.doujin.service.ContentService;
import cc.lupss.doujin.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
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
        return Result.ok().data("comics", contentMapper.selectComicsPage(page, null));
    }

    @GetMapping("/comic")
    public Result getComic(Integer id) {
        return Result.ok().data("comic", contentMapper.selectComic(id));
    }

    @GetMapping(value = "/comic/thumb", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] ComicThumb(Integer id) throws Exception {
        Comic contentDO = contentMapper.selectComic(id);
        File file = new File(resPath + contentDO.getImage());
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
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

    @GetMapping("/comic/upload")
    public Result ComicUploadInfo() {
        return Result.ok().data("selections", "");
    }
}
