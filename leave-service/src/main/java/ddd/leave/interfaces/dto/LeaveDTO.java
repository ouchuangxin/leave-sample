package ddd.leave.interfaces.dto;

import ddd.leave.domain.leave.repository.po.ApprovalInfoPO;
import lombok.Data;

import java.util.List;

@Data
public class LeaveDTO {

    String leaveId;
    ApplicantDTO applicantDTO;
    ApproverDTO approverDTO;
    String leaveType;

    String startTime;
    String endTime;
    int duration;
    String status;

    ApprovalInfoDTO currentApprovalInfoDTO;
    List<ApprovalInfoDTO> historyApprovalInfoDTOList;
}
