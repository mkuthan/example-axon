package example.conference.registrations.impl;

import example.conference.configuration.ConferenceConfiguration;
import example.conference.registrations.impl.domain.Order;
import example.conference.registrations.impl.domain.SeatsAvailability;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcedAggregateRoot;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.repository.LockManager;
import org.axonframework.repository.NullLockManager;
import org.axonframework.repository.Repository;
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
    public Repository<Order> orderRepository(EventBus eventBus, EventStore eventStore, LockManager lockManager) {
        return createEventSourcingRepository(Order.class, eventBus, eventStore, lockManager);
    }

    @Bean
    public Repository<SeatsAvailability> seatsAvailabilityRepository(EventBus eventBus, EventStore eventStore, LockManager lockManager) {
        return createEventSourcingRepository(SeatsAvailability.class, eventBus, eventStore, lockManager);
    }

    @Bean
    public LockManager lockManager() {
        return new NullLockManager();
    }

    private <T extends EventSourcedAggregateRoot> EventSourcingRepository<T> createEventSourcingRepository(Class<T> aggregateType, EventBus eventBus, EventStore eventStore, LockManager lockManager) {
        EventSourcingRepository<T> repository = new EventSourcingRepository<>(aggregateType, eventStore, lockManager);
        repository.setEventBus(eventBus);
        return repository;
    }

}
