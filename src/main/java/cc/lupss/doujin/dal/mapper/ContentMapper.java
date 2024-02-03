package cc.lupss.doujin.dal.mapper;

import cc.lupss.doujin.controller.vo.Comic;
import cc.lupss.doujin.controller.vo.ComicHome;
import cc.lupss.doujin.controller.vo.ModelTag;
import cc.lupss.doujin.controller.vo.TagNum;
import cc.lupss.doujin.dal.database.ContentDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContentMapper extends BaseMapper<ContentDO> {
    IPage<Comic> selectComicsPage(IPage<Comic> page, @Param(Constants.WRAPPER) QueryWrapper<Comic> wrapper);

    Comic selectComic(Integer id);

    IPage<ComicHome> selectHome(IPage<ComicHome> page);

    List<ModelTag> selectModelTag();
}
