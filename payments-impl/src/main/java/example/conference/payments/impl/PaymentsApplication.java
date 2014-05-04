package example.conference.payments.impl;

import example.conference.ConferenceConfiguration;
import example.conference.payments.impl.domain.Payment;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import({ConferenceConfiguration.class})
@ComponentScan
public class PaymentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }

    @Bean
    public EventSourcingRepository<Payment> orderRepository(EventBus eventBus, EventStore eventStore) {
        EventSourcingRepository<Payment> repository = new EventSourcingRepository<>(Payment.class, eventStore);
        repository.setEventBus(eventBus);
        return repository;

    }

}
