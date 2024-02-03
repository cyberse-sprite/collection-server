package cc.lupss.doujin.controller.vo;

import lombok.Data;

import java.util.List;

@Data
public class ModelTag {
    Integer id;
    String name;
    String title;
    List<TagNum> tags;
}
