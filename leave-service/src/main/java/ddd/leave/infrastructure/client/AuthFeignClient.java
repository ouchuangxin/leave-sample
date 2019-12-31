package ddd.leave.infrastructure.client;

import ddd.leave.domain.person.entity.Person;
import ddd.leave.infrastructure.common.api.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service", path = "/demo/auth")
public interface AuthFeignClient {

    @PostMapping(value = "/login")
    Response login(Person person);
}
