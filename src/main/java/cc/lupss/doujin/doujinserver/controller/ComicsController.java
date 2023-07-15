package cc.lupss.doujin.doujinserver.controller;

import cc.lupss.doujin.doujinserver.entity.Comic;
import cc.lupss.doujin.doujinserver.service.ComicService;
import cc.lupss.doujin.doujinserver.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ComicsController {
    @Resource
    ComicService comicService;

    @GetMapping("/comics")
    public Result getComics(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "0") Integer grade,
            @RequestParam(defaultValue = "0") Integer gore,
            @RequestParam(defaultValue = "2") Integer own,
            @RequestParam(defaultValue = "") String language,
            @RequestParam(defaultValue = "0") Integer tag) {
        Page<Comic> page = new Page(current, 16);
        List<String[]> orders = new ArrayList<>() {{
            add(new String[]{"orders", "true"});
            add(new String[]{"create_time", "false"});
            add(new String[]{"title", "true"});
            add(new String[]{"publish_time", "true"});
            add(new String[]{"update_time", "true"});
        }};
        System.out.println(language);
        return Result.ok().data("comics", comicService.pageCustom(page, grade, gore, language, own, tag, orders));
    }
}
