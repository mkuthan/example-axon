package example.conference.payments.impl;

import example.conference.configuration.ConferenceConfiguration;
import example.conference.payments.impl.domain.Payment;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.repository.LockManager;
import org.axonframework.repository.NullLockManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

@Configuration
@EnableAutoConfiguration
@Import({ConferenceConfiguration.class})
@ImportResource("classpath:/payments.xml")
@ComponentScan
public class PaymentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }

}
