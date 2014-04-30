package example.conference.payments;

import example.conference.registration.PlaceOrder;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/registration")
@RestController
public class PaymentController {

	@Autowired
	private CommandGateway commandGateway;

	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void placeOrder(@RequestBody PlaceOrder command) {
		commandGateway.send(command);
	}

}
