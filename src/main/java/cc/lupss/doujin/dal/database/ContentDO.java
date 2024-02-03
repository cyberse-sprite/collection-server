package cc.lupss.doujin.dal.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("content")
public class ContentDO {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField
    String title;
    @TableField
    String model;
    @TableField(jdbcType = JdbcType.TIMESTAMP)
    LocalDateTime publishTime;
    @TableField(jdbcType = JdbcType.TIMESTAMP)
    LocalDateTime createTime;
    @TableField(jdbcType = JdbcType.TIMESTAMP)
    LocalDateTime updateTime;
    @TableField
    String content;
    @TableField
    Integer orders;
}
