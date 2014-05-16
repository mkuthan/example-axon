package example.conference.payments.api

import example.conference.shared.Event

@Event
class PaymentReceived {
    String orderId
    String paymentId
}
