package cc.lupss.doujin.doujinserver.service;

import cc.lupss.doujin.doujinserver.entity.Comic;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

public interface ComicService extends IService<Comic> {
    IPage<Comic> pageCustom(Page<Comic> comicPage, Integer grade, Integer gore, String language, Integer own, Integer tag, List<String[]> orders);
    Comic getByIdWithTags(Serializable id);
    boolean saveCustom(Comic comic);
}
