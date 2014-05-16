package example.conference.management.impl.domain;

import example.conference.management.api.SeatTypeCreated;
import example.conference.management.api.SeatTypeUpdated;
import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(SeatType.class)
public class SeatTypeEventHandler {

    @Autowired
    private EventBus eventBus;

    @HandleAfterCreate
    public void publishSeatTypeCreatedEvent(SeatType seatType) {
        SeatTypeCreated event = new SeatTypeCreated();

        event.setSeatTypeId(seatType.getId());
        event.setType(seatType.getType());
        event.setQuantity(seatType.getQuantity());
        event.setPriceAmount(seatType.getPriceAmount());
        event.setPriceCurrency(seatType.getPriceCurrency());
        event.setConferenceId(seatType.getConference().getId());

        publishEvent(event);
    }

    @HandleAfterSave
    public void publishSeatTypeUpdatedEvent(SeatType seatType) {
        SeatTypeUpdated event = new SeatTypeUpdated();

        event.setSeatTypeId(seatType.getId());
        event.setType(seatType.getType());
        event.setQuantity(seatType.getQuantity());
        event.setPriceAmount(seatType.getPriceAmount());
        event.setPriceCurrency(seatType.getPriceCurrency());
        event.setConferenceId(seatType.getConference().getId());

        publishEvent(event);
    }

    private void publishEvent(Object event) {
        eventBus.publish(GenericEventMessage.asEventMessage(event));
    }

}
