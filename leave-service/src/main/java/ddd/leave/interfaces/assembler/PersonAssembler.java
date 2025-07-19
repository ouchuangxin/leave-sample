package ddd.leave.interfaces.assembler;

import ddd.leave.domain.person.entity.Person;
import ddd.leave.domain.person.entity.valueobject.PersonStatus;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import ddd.leave.infrastructure.util.DateUtil;
import ddd.leave.interfaces.dto.PersonDTO;

import java.text.ParseException;

public class PersonAssembler {

    public static PersonDTO toDTO(Person person){
        PersonDTO dto = new PersonDTO();
        dto.setPersonId(person.getPersonId());
        dto.setPersonType(person.getPersonType().getVal());
        dto.setPersonName(person.getPersonName());
        dto.setStatus(person.getStatus().getVal());
        dto.setCreateTime(DateUtil.formatDateTime(person.getCreateTime()));
        dto.setLastModifyTime(DateUtil.formatDateTime(person.getLastModifyTime()));
        dto.setLeaderId(person.getLeaderId());
        dto.setRoleLevel(person.getRoleLevel());
        return dto;
    }

    public static Person toDO(PersonDTO dto) throws ParseException {
        Person person = new Person();
        person.setPersonId(dto.getPersonId());
        person.setPersonType(PersonType.from(dto.getPersonType()));
        person.setPersonName(dto.getPersonName());
        person.setStatus(PersonStatus.from(dto.getStatus()));
        person.setCreateTime(DateUtil.parseDateTime(dto.getCreateTime()));
        person.setLastModifyTime(DateUtil.parseDateTime(dto.getLastModifyTime()));
        person.setLeaderId(dto.getLeaderId());
        person.setRoleLevel(dto.getRoleLevel());
        return person;
    }
}
