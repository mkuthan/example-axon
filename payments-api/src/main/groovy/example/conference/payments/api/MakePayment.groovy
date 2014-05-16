package example.conference.payments.api

import example.conference.shared.Command

@Command
class MakePayment {
    String paymentId
    String orderId
}
