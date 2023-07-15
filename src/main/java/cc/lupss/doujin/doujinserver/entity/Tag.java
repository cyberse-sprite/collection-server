package cc.lupss.doujin.doujinserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

@Data
@Table(comment = "标签")
public class Tag {
    @IsKey
    @IsAutoIncrement
    @TableId(type = IdType.AUTO)
    Integer id;
    @Column
    String value;
    @Column
    String type;
    @TableField(exist = false)
    Integer count;
    @TableField(exist = false)
    Integer image;
}
