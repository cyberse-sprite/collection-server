package cc.lupss.doujin.service;

import cc.lupss.doujin.dal.database.ContentDO;
import cc.lupss.doujin.dal.mapper.ContentMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, ContentDO> implements ContentService {
    @Resource
    ContentMapper contentMapper;

}
