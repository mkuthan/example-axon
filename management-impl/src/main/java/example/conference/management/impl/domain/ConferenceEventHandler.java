package example.conference.management.impl.domain;

import example.conference.management.api.ConferenceCreated;
import example.conference.management.api.ConferenceUpdated;
import org.axonframework.domain.GenericEventMessage;
import org.axonframework.eventhandling.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

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

        publishEvent(event);
    }

    @HandleAfterSave
    public void publishConferenceUpdatedEvent(Conference conference) {
        ConferenceUpdated event = new ConferenceUpdated();

        event.setConferenceId(conference.getId());
        event.setName(conference.getName());
        event.setLocation(conference.getLocation());

        publishEvent(event);
    }

    private void publishEvent(Object event) {
        eventBus.publish(GenericEventMessage.asEventMessage(event));
    }

}
