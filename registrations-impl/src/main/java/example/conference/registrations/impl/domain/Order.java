package example.conference.registrations.impl.domain;

import example.conference.registrations.api.OrderPlaced;
import example.conference.registrations.api.SeatQuantity;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class Order extends AbstractAnnotatedAggregateRoot<String> {

    private static final int DEFAULT_TIMEOUT = 3;

    @AggregateIdentifier
    private String orderId;

    private String conferenceId;

    private List<OrderItem> items;

    public Order(String orderId, String conferenceId, List<OrderItem> items) {
        requireNonNull(orderId);
        requireNonNull(conferenceId);
        requireNonNull(items);

        if (items.isEmpty()) {
            throw new IllegalArgumentException("Expected at least one order item.");
        }

        List<SeatQuantity> seats = items.stream().map(OrderItem::toSeatQuantity).collect(toList());
        apply(new OrderPlaced(orderId, conferenceId, seats, DEFAULT_TIMEOUT));
    }

    @EventSourcingHandler
    private void handle(OrderPlaced event) {
        this.orderId = event.getOrderId();
        this.conferenceId = event.getConferenceId();
        this.items = OrderItem.of(event.getSeats());
    }

    protected Order() {
    }
}
