package ddd.leave.infrastructure.client;

import ddd.leave.domain.person.entity.Person;
import ddd.leave.infrastructure.common.api.Response;
import org.springframework.web.bind.annotation.PostMapping;

public interface AuthClient {
    Response login(Person person);
}
