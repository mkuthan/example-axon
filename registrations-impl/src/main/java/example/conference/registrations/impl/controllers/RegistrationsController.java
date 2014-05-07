package example.conference.registrations.impl.controllers;

import example.conference.registrations.api.RegisterToConference;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/registrations")
@RestController
public class RegistrationsController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/registerToConference", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody RegisterToConference command) {
        commandGateway.send(command);
    }

}
