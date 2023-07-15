package cc.lupss.doujin.doujinserver.controller;

import cc.lupss.doujin.doujinserver.entity.Relationship;
import cc.lupss.doujin.doujinserver.entity.Tag;
import cc.lupss.doujin.doujinserver.mapper.RelationshipMapper;
import cc.lupss.doujin.doujinserver.mapper.TagMapper;
import cc.lupss.doujin.doujinserver.service.RelationshipService;
import cc.lupss.doujin.doujinserver.service.TagService;
import cc.lupss.doujin.doujinserver.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class HomeController {
    @Resource
    TagService tagService;
    @Resource
    RelationshipMapper relationshipMapper;
    @Resource
    TagMapper tagMapper;
    @Resource
    RelationshipService relationshipService;

    @GetMapping("/home")
    public Result home() {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("type", "origin");
        List<Tag> tags = tagMapper.selectTagsWithCount(wrapper);
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            QueryWrapper<Relationship> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("tid", tag.getId());
            wrapper1.last("limit 1");
            wrapper1.orderByDesc("id");
            Relationship relationship = relationshipService.getOne(wrapper1);
            tag.setImage(relationship.getCid());
            tags.set(i, tag);
        }
        return Result.ok().data("home", tags);
    }
}
