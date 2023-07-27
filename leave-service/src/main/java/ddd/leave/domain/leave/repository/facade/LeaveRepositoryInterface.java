package ddd.leave.domain.leave.repository.facade;

import ddd.leave.domain.leave.repository.po.ApprovalInfoPO;
import ddd.leave.domain.leave.repository.po.LeaveEventPO;
import ddd.leave.domain.leave.repository.po.LeavePO;

import java.util.List;

public interface LeaveRepositoryInterface {

    void save(LeavePO leavePO);

    void saveEvent(LeaveEventPO leaveEventPO);

    void saveApprovalInfo(ApprovalInfoPO approvalInfoPO);

    LeavePO findById(String id);

    List<ApprovalInfoPO> findByLeaveId(String leaveId);

    List<LeavePO> queryByApplicantId(String applicantId);

    List<LeavePO> queryByApproverId(String approverId);

}