package ddd.leave.domain.leave.repository.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler;
import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.leave.entity.valueobject.Status;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;
import java.util.*;

@TableName("t_leave")
@Data
public class LeavePO {
    @TableId
    String id;
    String applicantId;
    String applicantName;

    String applicantType;

    String approverId;
    String approverName;

    String leaveType;

    String status;

    Date startTime;
    Date endTime;
    int duration;


}
