package example.conference.payments.impl.domain;

import example.conference.payments.api.MakePayment;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/payments")
@RestController
public class PaymentController {

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/makePayment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody MakePayment command, UriComponentsBuilder builder) {
        commandGateway.send(command);
    }

}
