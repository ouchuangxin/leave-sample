package ddd.leave.application.service;

import ddd.leave.domain.person.entity.Person;
import ddd.leave.domain.person.service.PersonDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonApplicationService {

    @Autowired
    PersonDomainService personDomainService;

    public void create(Person person) {
        personDomainService.create(person);
    }

    public void update(Person person) {
        personDomainService.update(person);
    }

    public void deleteById(String personId) {
        personDomainService.deleteById(personId);
    }

    public Person findById(String personId) {
        return null;
    }

    public Person findFirstApprover(String applicantId, int leaderMaxLevel) {
        return personDomainService.findFirstApprover(applicantId, leaderMaxLevel);
    }


}
