package ddd.leave.domain.person.service;

import ddd.leave.domain.person.entity.Person;
import ddd.leave.domain.person.entity.valueobject.PersonStatus;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import ddd.leave.domain.person.repository.facade.PersonRepository;
import ddd.leave.domain.person.repository.po.PersonPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonFactory {

    @Autowired
    PersonRepository personRepository;

    public PersonPO createPersonPO(Person person){
        PersonPO personPO = new PersonPO();
        personPO.setPersonId(person.getPersonId());
        personPO.setPersonName(person.getPersonName());
        personPO.setRoleLevel(person.getRoleLevel());
        personPO.setPersonType(person.getPersonType().getVal());
        personPO.setCreateTime(person.getCreateTime());
        personPO.setLastModifyTime(person.getLastModifyTime());
        personPO.setStatus(person.getStatus().getVal());
        personPO.setLeaderId(person.getLeaderId());
        personPO.setRoleLevel(person.getRoleLevel());
        return personPO;
    }

    public Person createPerson(PersonPO po){
        Person person = new Person();
        person.setPersonId(po.getPersonId());
        person.setPersonType(PersonType.from(po.getPersonType()));
        person.setRoleLevel(po.getRoleLevel());
        person.setPersonName(po.getPersonName());
        person.setStatus(PersonStatus.from(po.getStatus()));
        person.setCreateTime(po.getCreateTime());
        person.setLastModifyTime(po.getLastModifyTime());
        person.setLeaderId(po.getLeaderId());
        person.setRoleLevel(po.getRoleLevel());
        return person;
    }

    public Person getPerson(PersonPO personPO){
        personPO = personRepository.findById(personPO.getPersonId());
        return createPerson(personPO);
    }

}
