package example.conference.configuration;

import org.axonframework.auditing.AuditLogger;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.domain.EventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;

public class DefaultAuditLogger implements AuditLogger {

    private static final Logger logger = LoggerFactory.getLogger(DefaultAuditLogger.class);

    @Override
    public void logSuccessful(Object command, Object returnValue, List<EventMessage> events) {
        if (logger.isTraceEnabled()) {
            Object payload = extractCommandPayload(command);
            StringBuilder sb = new StringBuilder()
                    .append("Successful execution of command: ")
                    .append("\n\t ").append(payload.toString());

            if (returnValue != null) {
                sb.append("\n\t Return value: ").append(returnValue.toString());
            }

            if (events != null && events.size() > 0) {
                sb.append("\n\t List of events fired during execution:");
                for (EventMessage event : events) {
                    sb.append("\n\t\t ").append(event.getPayload().toString());
                }
            }

            logger.trace(sb.toString());
        }
    }

    @Override
    public void logFailed(Object command, Throwable failureCause, List<EventMessage> events) {
        if (logger.isTraceEnabled()) {
            Object payload = extractCommandPayload(command);

            StringBuilder sb = new StringBuilder()
                    .append("Failure occurred during execution of command: ")
                    .append("\n\t ").append(payload.toString())
                    .append("\n\t Exception name: ")
                    .append(failureCause.getClass().getName())
                    .append("\n\t Exception message: ")
                    .append(failureCause.getMessage());
            if (events != null && events.size() > 0) {
                sb.append("\n\t List of events fired during execution:");
                for (EventMessage event : events) {
                    sb.append("\n\t\t ").append(event.getPayload().toString());
                }
            }

            logger.trace(sb.toString(), failureCause);
        }
    }

    private Object extractCommandPayload(Object command) {
        if (command instanceof GenericCommandMessage) {
            return ((GenericCommandMessage) command).getPayload();
        } else {
            return command;
        }
    }
}

