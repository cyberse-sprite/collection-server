package cc.lupss.doujin.controller.vo;

import cc.lupss.doujin.dal.database.ContentDO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Comic {
    String id;
    String title;
    String content;
    String image;
    LocalDateTime publishTime;
    LocalDateTime createTime;
    LocalDateTime updateTime;
    List<ContentDO> tags;
}
