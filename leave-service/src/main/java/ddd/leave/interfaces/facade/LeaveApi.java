package ddd.leave.interfaces.facade;

import ddd.leave.application.service.LeaveApplicationService;
import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.domain.person.entity.Person;
import ddd.leave.infrastructure.common.api.Response;
import ddd.leave.interfaces.assembler.LeaveAssembler;
import ddd.leave.interfaces.dto.LeaveDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leave")
@Slf4j
public class LeaveApi {

    @Autowired
    LeaveApplicationService leaveApplicationService;

    @PostMapping
    public Response createLeaveInfo(LeaveDTO leaveDTO){
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveApplicationService.createLeaveInfo(leave);
        return Response.ok();
    }

    @PutMapping
    public Response updateLeaveInfo(LeaveDTO leaveDTO){
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveApplicationService.updateLeaveInfo(leave);
        return Response.ok();
    }

    @PostMapping("/submit")
    public Response submitApproval(LeaveDTO leaveDTO){
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveApplicationService.submitApproval(leave);
        return Response.ok();
    }

    @PostMapping("/{leaveId}")
    public Response findById(@PathVariable String leaveId){
        Leave leave = leaveApplicationService.getLeaveInfo(leaveId);
        return Response.ok(LeaveAssembler.toDTO(leave));
    }

    /**
     * 根据申请人查询所有请假单
     * @param applicantId
     * @return
     */
    @PostMapping("/query/applicant/{applicantId}")
    public Response queryByApplicant(@PathVariable String applicantId){
        List<Leave> leaveList = leaveApplicationService.queryLeaveInfosByApplicant(applicantId);
        List<LeaveDTO> leaveDTOList = leaveList.stream().map(leave -> LeaveAssembler.toDTO(leave)).collect(Collectors.toList());
        return Response.ok(leaveDTOList);
    }

    /**
     * 根据审批人id查询待审批请假单（待办任务）
     * @param approverId
     * @return
     */
    @PostMapping("/query/approver/{approverId}")
    public Response queryByApprover(@PathVariable String approverId){
        List<Leave> leaveList = leaveApplicationService.queryLeaveInfosByApprover(approverId);
        List<LeaveDTO> leaveDTOList = leaveList.stream().map(leave -> LeaveAssembler.toDTO(leave)).collect(Collectors.toList());
        return Response.ok(leaveDTOList);
    }
}
