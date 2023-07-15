package cc.lupss.doujin.doujinserver.service.impl;

import cc.lupss.doujin.doujinserver.entity.Tag;
import cc.lupss.doujin.doujinserver.mapper.TagMapper;
import cc.lupss.doujin.doujinserver.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
}
