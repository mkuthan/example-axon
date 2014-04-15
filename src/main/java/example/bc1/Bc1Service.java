package example.bc1;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class Bc1Service {

	@CommandHandler
	public void command(Bc1Command command) {
		System.out.println(">>>> " + command);
	}
}
