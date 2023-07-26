package ddd.leave.domain.rule.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ddd.leave.domain.rule.entity.ApprovalRule;
import ddd.leave.domain.rule.repository.po.ApprovalRulePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApprovalRuleMapper extends BaseMapper<ApprovalRulePO> {
    ApprovalRulePO findRule(String personType, String leaveType, long duration);
}
