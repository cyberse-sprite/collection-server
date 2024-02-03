package cc.lupss.doujin.dal.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("relationship")
public class RelationshipDO {
    @TableId(type = IdType.AUTO)
    Integer id;
    @TableField
    Integer tid;
    @TableField
    Integer cid;
}
