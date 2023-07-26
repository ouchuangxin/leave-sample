package ddd.leave.domain.person.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_relationship")
public class RelationshipPO {
    @TableId
    String id;
    String personId;
    String leaderId;
}
