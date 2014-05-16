package example.conference.registrations.api

import example.conference.shared.Event

@Event
class OrderConfirmed {
    String orderId
}
