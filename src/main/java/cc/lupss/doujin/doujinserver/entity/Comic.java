package cc.lupss.doujin.doujinserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Table(comment = "漫画类型")
public class Comic {
    @IsKey
    @IsAutoIncrement
    @TableId(type = IdType.AUTO)
    Integer id;
    @Column(type = MySqlTypeConstant.TEXT)
    String title;
    @Column(type = MySqlTypeConstant.TEXT)
    String image;
    @Column(length = 10)
    String language;
    @Column(type = MySqlTypeConstant.TIMESTAMP)
    Date publishTime;
    @Column(type = MySqlTypeConstant.TIMESTAMP)
    Date createTime;
    @Column(type = MySqlTypeConstant.TIMESTAMP)
    Date updateTime;
    @Column(type = MySqlTypeConstant.TEXT)
    String intro;
    @Column(type = MySqlTypeConstant.TEXT)
    String filePath;
    @Column
    boolean grade;
    @Column
    boolean gore;
    @Column
    Integer orders;
    @Column
    Boolean own;
    @TableField(exist = false)
    List<Tag> tags;
}
