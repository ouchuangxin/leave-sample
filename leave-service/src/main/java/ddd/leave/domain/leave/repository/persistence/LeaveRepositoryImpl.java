package ddd.leave.domain.leave.repository.persistence;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ddd.leave.domain.leave.repository.facade.LeaveRepositoryInterface;
import ddd.leave.domain.leave.repository.mapper.*;
import ddd.leave.domain.leave.repository.po.ApprovalInfoPO;
import ddd.leave.domain.leave.repository.po.LeaveEventPO;
import ddd.leave.domain.leave.repository.po.LeavePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * persist entity and handle event in repository
 */
@Repository
public class LeaveRepositoryImpl implements LeaveRepositoryInterface {

    @Autowired
    LeaveMapper leaveMapper;
    @Autowired
    ApprovalInfoMapper approvalInfoMapper;

    @Autowired
    LeaveEventMapper leaveEventMapper;

    public void save(LeavePO leavePO) {
        LeavePO po = findById(leavePO.getId());
        if (null == po) {
            leaveMapper.insert(leavePO);
        } else {
            leaveMapper.updateById(leavePO);
        }
    }

    public void saveEvent(LeaveEventPO leaveEventPO){
        leaveEventMapper.insert(leaveEventPO);
    }

    @Override
    public LeavePO findById(String id) {
        return leaveMapper.selectById(id);
    }

    @Override
    public List<LeavePO> queryByApplicantId(String applicantId) {
        List<LeavePO> leavePOList = leaveMapper.queryByApplicantId(applicantId);
        List<LeavePO> leavePOList2 = leaveMapper.selectList(new QueryWrapper<LeavePO>().eq("applicant_id", applicantId));
        assert leavePOList.size() == leavePOList2.size();
        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoMapper.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });
        return leavePOList;
    }

    @Override
    public List<LeavePO> queryByApproverId(String approverId) {
        List<LeavePO> leavePOList = leaveMapper.queryByApproverId(approverId);
        List<LeavePO> leavePOList2 = leaveMapper.selectList(new QueryWrapper<LeavePO>().eq("approver_id", approverId));
        assert leavePOList.size() == leavePOList2.size();

        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoMapper.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });
        return leavePOList;
    }

}
