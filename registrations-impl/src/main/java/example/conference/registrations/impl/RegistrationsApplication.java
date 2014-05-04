package example.conference.registrations.impl;

import example.conference.ConferenceConfiguration;
import example.conference.registrations.impl.domain.Order;
import example.conference.registrations.impl.domain.SeatsAvailability;
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
public class RegistrationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationsApplication.class, args);
    }

    @Bean
    public EventSourcingRepository<Order> orderRepository(EventBus eventBus, EventStore eventStore) {
        EventSourcingRepository<Order> repository = new EventSourcingRepository<>(Order.class, eventStore);
        repository.setEventBus(eventBus);
        return repository;

    }

    @Bean
    public EventSourcingRepository<SeatsAvailability> seatsAvailabilityRepository(EventBus eventBus, EventStore eventStore) {
        EventSourcingRepository<SeatsAvailability> repository = new EventSourcingRepository<>(SeatsAvailability.class, eventStore);
        repository.setEventBus(eventBus);
        return repository;

    }


}
