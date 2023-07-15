package cc.lupss.doujin.doujinserver.mapper;

import cc.lupss.doujin.doujinserver.entity.Comic;
import cc.lupss.doujin.doujinserver.entity.Tag;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    @Select("SELECT t.* FROM tag t LEFT JOIN ( SELECT r.* FROM relationship r LEFT JOIN comic c ON r.cid = c.id ) x ON t.id = x.tid WHERE x.cid = #{tags}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "value", property = "value")
    })
    public List<Tag> selectTagsByCid(@Param("tags") Integer tags);

    @Select("select * from tag ${ew.customSqlSegment}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "count", column = "id",
                    one = @One(select = "cc.lupss.doujin.doujinserver.mapper.RelationshipMapper.countRelationshipByTid"))
    })
    public List<Tag> selectTagsWithCount(@Param(Constants.WRAPPER) Wrapper<Tag> wrapper);
}
