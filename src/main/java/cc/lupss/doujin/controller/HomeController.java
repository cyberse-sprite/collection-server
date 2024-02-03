package cc.lupss.doujin.controller;

import cc.lupss.doujin.controller.vo.ComicHome;
import cc.lupss.doujin.dal.mapper.ContentMapper;
import cc.lupss.doujin.util.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HomeController {
    @Resource
    ContentMapper contentMapper;

    @GetMapping("/home")
    public Result home(Integer current) {
        if (current == null) current = 1;
        Page<ComicHome> page = new Page<>(current, 16);
        return Result.ok().data("home", contentMapper.selectHome(page));
    }
}
