package cc.lupss.doujin.doujinserver.mapper;

import cc.lupss.doujin.doujinserver.entity.Relationship;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface RelationshipMapper extends MppBaseMapper<Relationship> {
    @Select("SELECT tid,count(tid) as c FROM relationship where tid=#{tid} group by tid")
    @Results({
            @Result(column = "c", property = "count"),
            @Result(column = "tid", property = "id", id = true)
    })
    public Integer countRelationshipByTid(@Param("tid") Integer tid);
}
