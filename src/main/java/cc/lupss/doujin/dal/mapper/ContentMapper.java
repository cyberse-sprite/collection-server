package cc.lupss.doujin.doujinserver.dal.mapper;

import cc.lupss.doujin.doujinserver.controller.vo.Comic;
import cc.lupss.doujin.doujinserver.dal.database.ContentDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContentMapper extends BaseMapper<ContentDO> {
    IPage<Comic> selectComicsPage(IPage<Comic> page, @Param(Constants.WRAPPER) QueryWrapper<Comic> wrapper);
}
