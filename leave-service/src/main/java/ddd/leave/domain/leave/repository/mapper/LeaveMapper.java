package ddd.leave.domain.leave.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ddd.leave.domain.leave.repository.po.LeavePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeaveMapper extends BaseMapper<LeavePO> {

    List<LeavePO> queryByApplicantId(String applicantId);

    List<LeavePO> queryByApproverId(String approverId);
}
