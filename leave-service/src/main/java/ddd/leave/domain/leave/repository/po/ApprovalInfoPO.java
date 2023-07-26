package ddd.leave.domain.leave.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_approval_info")
public class ApprovalInfoPO {
    @TableId
    String approvalInfoId;
    String leaveId;
    String applicantId;
    String approverId;
    int approverLevel;
    String approverName;
    String msg;
    long time;

}
