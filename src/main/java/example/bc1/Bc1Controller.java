package example.bc1;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bc1")
@RestController
public class Bc1Controller {

	@Autowired
	private CommandGateway commandGateway;

	@RequestMapping(value = "/command", method = RequestMethod.POST)
	public void command(Bc1Command command) {
		commandGateway.send(command);
	}

}
