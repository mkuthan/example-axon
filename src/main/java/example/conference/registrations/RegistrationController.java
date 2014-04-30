package example.conference.registrations;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/registration")
@RestController
public class RegistrationController {

	@Autowired
	private CommandGateway commandGateway;

	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void placeOrder(@RequestBody PlaceOrder command) {
		commandGateway.send(command);
	}

}
