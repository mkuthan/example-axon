package example.conference.management.impl.domain;

import example.conference.management.api.ConferenceCreated;
import example.conference.management.api.ConferenceUpdated;
import example.conference.management.api.Money;
import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RepositoryEventHandler(Conference.class)
public class ConferenceEventHandler {

    @Autowired
    private EventBus eventBus;

    @HandleAfterCreate
    public void publishConferenceCreatedEvent(Conference conference) {
        ConferenceCreated event = new ConferenceCreated();
        event.setConferenceId(conference.getId());
        event.setName(conference.getName());
        event.setLocation(conference.getLocation());
        //event.setSeatTypes(assembeSeatTypes(conference.getSeatTypes()));

        publishEvent(event);
    }

    @HandleAfterSave
    public void publishConferenceUpdated(Conference conference) {
        ConferenceUpdated event = new ConferenceUpdated();
        event.setConferenceId(conference.getId());
        event.setName(conference.getName());
        event.setLocation(conference.getLocation());
        //event.setSeatTypes(assembeSeatTypes(conference.getSeatTypes()));

        publishEvent(event);
    }

    private void publishEvent(Object event) {
        eventBus.publish(GenericEventMessage.asEventMessage(event));
    }

    private List<example.conference.management.api.SeatType> assembeSeatTypes(List<SeatType> seatTypes) {
        return seatTypes.stream().map(seatType -> new example.conference.management.api.SeatType(
                seatType.getType(),
                seatType.getQuantity(),
                new Money(
                        seatType.getPriceAmount(),
                        seatType.getPriceCurrency())
        )).collect(Collectors.toList());
    }

}
