package cc.lupss.doujin.doujinserver.controller;

import cc.lupss.doujin.doujinserver.entity.Tag;
import cc.lupss.doujin.doujinserver.mapper.TagMapper;
import cc.lupss.doujin.doujinserver.service.TagService;
import cc.lupss.doujin.doujinserver.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.comparators.NullComparator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
public class TagController {
    @Resource
    TagService tagService;
    @Resource
    TagMapper tagMapper;

    @GetMapping("/tags")
    public Result getTags() {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("type");
        wrapper.in("type","relationship","charactor","xp");
                List < Tag > list = tagMapper.selectTagsWithCount(wrapper);
        NullComparator<Integer> nullComparator = new NullComparator<>();
        list.sort(Comparator.nullsLast(Comparator.comparing(Tag::getCount, nullComparator).reversed()));
        return Result.ok().data("tags", list);
    }
}
