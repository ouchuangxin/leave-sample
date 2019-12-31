package ddd.leave.interfaces.facade;

import ddd.leave.application.service.PersonApplicationService;
import ddd.leave.domain.person.entity.Person;
import ddd.leave.infrastructure.common.api.Response;
import ddd.leave.interfaces.assembler.PersonAssembler;
import ddd.leave.interfaces.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonApi {

    @Autowired
    PersonApplicationService personApplicationService;

    @PostMapping
    public Response create(PersonDTO personDTO) {
        try {
            personApplicationService.create(PersonAssembler.toDO(personDTO));
            return Response.ok();
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
    }

    @PutMapping
    public Response update(PersonDTO personDTO) {
        try {
            personApplicationService.update(PersonAssembler.toDO(personDTO));
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
        return Response.ok();
    }

    @DeleteMapping("/{personId}")
    public Response delete(@PathVariable String personId) {
        personApplicationService.deleteById(personId);
        return Response.ok();
    }

    @GetMapping("/{personId}")
    public Response get(@PathVariable String personId) {
        Person person = personApplicationService.findById(personId);
        return Response.ok(PersonAssembler.toDTO(person));
    }

    @GetMapping("/findFirstApprover")
    public Response findFirstApprover(@RequestParam String applicantId, @RequestParam int leaderMaxLevel) {
        Person person = personApplicationService.findFirstApprover(applicantId, leaderMaxLevel);
        return Response.ok(PersonAssembler.toDTO(person));
    }

}
