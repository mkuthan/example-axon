package example.conference.registrations.api

import example.conference.shared.Command

@Command
class ConfirmOrder {
    String orderId
}
