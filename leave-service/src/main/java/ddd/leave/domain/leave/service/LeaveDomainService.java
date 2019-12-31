package ddd.leave.domain.leave.service;

import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.leave.entity.valueobject.ApprovalType;
import ddd.leave.domain.leave.entity.valueobject.Approver;
import ddd.leave.domain.leave.event.LeaveEvent;
import ddd.leave.domain.leave.event.LeaveEventType;
import ddd.leave.domain.leave.repository.facade.LeaveRepositoryInterface;
import ddd.leave.domain.leave.repository.po.LeavePO;
import ddd.leave.infrastructure.common.event.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LeaveDomainService {

    @Autowired
    EventPublisher eventPublisher;
    @Autowired
    LeaveRepositoryInterface leaveRepositoryInterface;
    @Autowired
    LeaveFactory leaveFactory;

    @Transactional
    public void createLeave(Leave leave, int leaderMaxLevel, Approver approver) {
        leave.setLeaderMaxLevel(leaderMaxLevel);
        leave.setApprover(approver);
        leave.create();
        leaveRepositoryInterface.save(leaveFactory.createLeavePO(leave));
        LeaveEvent event = LeaveEvent.create(LeaveEventType.CREATE_EVENT, leave);
        leaveRepositoryInterface.saveEvent(leaveFactory.createLeaveEventPO(event));
        eventPublisher.publish(event);
    }

    @Transactional
    public void updateLeaveInfo(Leave leave) {
        LeavePO po = leaveRepositoryInterface.findById(leave.getId());
        if (null == po) {
            throw new RuntimeException("leave does not exist");
        }
        leaveRepositoryInterface.save(leaveFactory.createLeavePO(leave));
    }

    @Transactional
    public void submitApproval(Leave leave, Approver approver) {
        LeaveEvent event;
        if ( ApprovalType.REJECT == leave.getCurrentApprovalInfo().getApprovalType()) {
            //reject, then the leave is finished with REJECTED status
            leave.reject(approver);
            event = LeaveEvent.create(LeaveEventType.REJECT_EVENT, leave);
        } else {
            if (approver != null) {
                //agree and has next approver
                leave.agree(approver);
                event = LeaveEvent.create(LeaveEventType.AGREE_EVENT, leave);
            } else {
                //agree and hasn't next approver, then the leave is finished with APPROVED status
                leave.finish();
                event = LeaveEvent.create(LeaveEventType.APPROVED_EVENT, leave);
            }
        }
        leave.addHistoryApprovalInfo(leave.getCurrentApprovalInfo());
        leaveRepositoryInterface.save(leaveFactory.createLeavePO(leave));
        leaveRepositoryInterface.saveEvent(leaveFactory.createLeaveEventPO(event));
        eventPublisher.publish(event);
    }

    public Leave getLeaveInfo(String leaveId) {
        LeavePO leavePO = leaveRepositoryInterface.findById(leaveId);
        return leaveFactory.getLeave(leavePO);
    }

    public List<Leave> queryLeaveInfosByApplicant(String applicantId) {
        List<LeavePO> leavePOList = leaveRepositoryInterface.queryByApplicantId(applicantId);
        return leavePOList.stream()
                .map(leavePO -> leaveFactory.getLeave(leavePO))
                .collect(Collectors.toList());
    }

    public List<Leave> queryLeaveInfosByApprover(String approverId) {
        List<LeavePO> leavePOList = leaveRepositoryInterface.queryByApproverId(approverId);
        return leavePOList.stream()
                .map(leavePO -> leaveFactory.getLeave(leavePO))
                .collect(Collectors.toList());
    }
}