package ddd.leave.domain.person.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ddd.leave.domain.person.repository.po.PersonPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper extends BaseMapper<PersonPO> {
    PersonPO findLeaderByPersonId(String personId);
}
