package cc.lupss.doujin.doujinserver.service.impl;

import cc.lupss.doujin.doujinserver.entity.Comic;
import cc.lupss.doujin.doujinserver.entity.Relationship;
import cc.lupss.doujin.doujinserver.entity.Tag;
import cc.lupss.doujin.doujinserver.mapper.ComicMapper;
import cc.lupss.doujin.doujinserver.mapper.RelationshipMapper;
import cc.lupss.doujin.doujinserver.mapper.TagMapper;
import cc.lupss.doujin.doujinserver.service.ComicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterGraphics;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComicServiceImpl extends ServiceImpl<ComicMapper, Comic> implements ComicService {
    @Resource
    ComicMapper comicMapper;
    @Resource
    RelationshipMapper relationshipMapper;
    @Resource
    TagMapper tagMapper;

    public IPage<Comic> pageCustom(Page<Comic> comicPage, Integer grade, Integer gore, String language, Integer own, Integer tag, List<String[]> orders) {
        QueryWrapper<Comic> wrapper = new QueryWrapper<>();
        if (tag > 0) {
            QueryWrapper<Relationship> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("tid", tag);
            List<Relationship> list = relationshipMapper.selectList(wrapper1);
            if (list.size() > 0) {
                List<Integer> collect = list.stream().map(Relationship::getCid).collect(Collectors.toList());
                wrapper.in("id", collect);
            } else {
                wrapper.eq("id", 0);
                return page(comicPage, wrapper);
            }
        }
        if (grade == 0) {
            wrapper.eq("grade", false);
        } else if (grade == 1) {
            wrapper.eq("grade", true);
        }
        if (gore == 0) {
            wrapper.eq("gore", false);
        } else if (gore == 1) {
            wrapper.eq("gore", true);
        }
        if (!language.equals("")) {
            System.out.println(language);
            wrapper.in("language", language);
        }
        if (own == 0) {
            wrapper.eq("own", false);
        } else if (own == 1) {
            wrapper.eq("own", true);
        }
        for (String[] order : orders) {
            wrapper.orderBy(true, Boolean.parseBoolean(order[1]), order[0]);
        }
        return comicMapper.selectListWithTags(comicPage, wrapper);
    }

    public boolean saveCustom(Comic comic) {
        comic.setCreateTime(new Date());
        comic.setUpdateTime(new Date());
        if (comicMapper.insert(comic) > 0) {
            List<Tag> tags = comic.getTags();
            for (Tag tag : tags) {
                QueryWrapper<Tag> wrapper = new QueryWrapper<>();
                wrapper.eq("value", tag.getValue());
                Tag getTag = tagMapper.selectOne(wrapper);
                Integer tid = 0;
                if (getTag != null) {
                    tid = getTag.getId();
                } else {
                    tagMapper.insert(tag);
                    tid = tag.getId();
                }
                QueryWrapper<Relationship> wrapper1 = new QueryWrapper<>();
                wrapper1.eq("cid", comic.getId());
                wrapper1.eq("tid", tid);
                if (relationshipMapper.selectOne(wrapper1) == null) {
                    Relationship relationship = new Relationship();
                    relationship.setCid(comic.getId());
                    relationship.setTid(tid);
                    relationshipMapper.insert(relationship);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public Comic getByIdWithTags(Serializable id) {
        return comicMapper.selectOneByIdWithTags(id);
    }

}
