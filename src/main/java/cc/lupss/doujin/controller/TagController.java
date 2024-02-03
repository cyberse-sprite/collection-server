package cc.lupss.doujin.controller;

import cc.lupss.doujin.dal.mapper.ContentMapper;
import cc.lupss.doujin.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TagController {
    @Resource
    ContentMapper contentMapper;

    @GetMapping("/tags")
    public Result tags() {
        return Result.ok().data("tags", contentMapper.selectModelTag());
    }
}
