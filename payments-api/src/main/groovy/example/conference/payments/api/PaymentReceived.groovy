package example.conference.payments.api

import groovy.transform.Canonical

@Canonical
class PaymentReceived {
    String orderId
    String paymentId
}
