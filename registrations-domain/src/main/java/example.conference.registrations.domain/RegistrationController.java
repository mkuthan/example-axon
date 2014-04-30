package example.conference.registrations.domain;

import example.conference.registrations.api.RegisterToConference;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/registrations")
@RestController
public class RegistrationController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/registerToConference", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody RegisterToConference command, UriComponentsBuilder builder) {
        commandGateway.send(command);
    }

}
