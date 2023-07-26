package ddd.leave.domain.person.repository.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import ddd.leave.domain.person.repository.po.PersonPO;
import ddd.leave.domain.person.repository.facade.PersonRepository;
import ddd.leave.domain.person.repository.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    PersonMapper personMapper;

    @Override
    public void insert(PersonPO personPO) {
        personMapper.insert(personPO);
    }

    @Override
    public void update(PersonPO personPO) {
        personMapper.update(personPO, new LambdaQueryWrapper<>(personPO).eq(po -> po.getPersonId(), personPO.getPersonId()));
    }

    @Override
    public PersonPO findById(String id) {
        return Optional.ofNullable(personMapper.selectById(id)).orElseThrow(() -> new RuntimeException("未找到用户"));
    }

    @Override
    public PersonPO findLeaderByPersonId(String personId) {
        return personMapper.findLeaderByPersonId(personId);
    }

}