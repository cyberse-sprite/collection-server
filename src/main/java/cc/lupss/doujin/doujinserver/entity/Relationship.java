package cc.lupss.doujin.doujinserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

@Data
@Table(comment = "关系")
public class Relationship {
    @IsKey
    @IsAutoIncrement
    @TableId(type = IdType.AUTO)
    Integer id;
    @Column
    Integer tid;
    @Column
    Integer cid;
}
