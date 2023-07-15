package cc.lupss.doujin.doujinserver.service.impl;

import cc.lupss.doujin.doujinserver.entity.Relationship;
import cc.lupss.doujin.doujinserver.mapper.RelationshipMapper;
import cc.lupss.doujin.doujinserver.service.RelationshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RelationshipServiceImpl extends ServiceImpl<RelationshipMapper, Relationship> implements RelationshipService {
}
