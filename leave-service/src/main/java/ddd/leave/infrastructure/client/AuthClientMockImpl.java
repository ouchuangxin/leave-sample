package ddd.leave.infrastructure.client;

import ddd.leave.domain.person.entity.Person;
import ddd.leave.infrastructure.common.api.Response;
import org.springframework.stereotype.Service;

@Service
public class AuthClientMockImpl implements AuthClient{
    @Override
    public Response login(Person person) {
        return Response.ok();
    }
}
