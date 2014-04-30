package example.conference.payments.api

import groovy.transform.Canonical

@Canonical
class MakePayment {
    String orderId
    String paymentId
}
