package ddd.leave.domain.rule.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;

@TableName("t_approval_rule")
@Data
public class ApprovalRulePO {

    @TableId
    String id;

    @TableField(typeHandler = MybatisEnumTypeHandler.class)
    LeaveType leaveType;

    @TableField(typeHandler = MybatisEnumTypeHandler.class)
    PersonType personType;

    long duration;

    int maxLeaderLevel;
}
