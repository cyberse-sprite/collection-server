package cc.lupss.doujin.doujinserver.mapper;

import cc.lupss.doujin.doujinserver.entity.Comic;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;

@Mapper
public interface ComicMapper extends BaseMapper<Comic> {
    @Select("select * from comic ${ew.customSqlSegment}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "tags", column = "id",
                    many = @Many(select = "cc.lupss.doujin.doujinserver.mapper.TagMapper.selectTagsByCid"))
    })
    public IPage<Comic> selectListWithTags(Page<Comic> page, @Param(Constants.WRAPPER) Wrapper<Comic> wrapper);

    @Select("select * from comic where id=#{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "tags", column = "id",
                    many = @Many(select = "cc.lupss.doujin.doujinserver.mapper.TagMapper.selectTagsByCid"))
    })
    public Comic selectOneByIdWithTags(Serializable id);
}
