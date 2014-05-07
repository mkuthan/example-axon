package example.conference.configuration;

import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventAuditListener {

    private static final Logger logger = LoggerFactory.getLogger(EventAuditListener.class);

    @EventHandler
    public void audit(EventMessage event) {
        if (logger.isTraceEnabled()) {
            logger.trace("Domain event published: {}", event.getPayload().toString());
        }
    }

}
