package example.conference.client

import com.jayway.restassured.RestAssured
import com.jayway.restassured.http.ContentType
import org.apache.http.HttpHeaders
import org.apache.http.HttpStatus

import static com.jayway.restassured.RestAssured.given
import static example.conference.client.ConferenceClientConstants.*
import static example.conference.client.ConferenceClientUtils.*

RestAssured.requestContentType(ContentType.JSON)

def qconId = generateId();
def qconHref = createConference(qconId, 'QCon 2014', 'New York')

waitForUser()

def qconConferenceId = generateId();
createSeatType(qconConferenceId, 'Conference', 800, 1600, 'USD', qconHref)

waitForUser()

def qconTutorialsId = generateId();
createSeatType(qconTutorialsId, 'Tutorials', 200, 300, 'USD', qconHref)

waitForUser()

// Register to Conference
def qconOrderId = generateId();
registerToConference(qconOrderId, qconId,
        [
                [seatTypeId: qconConferenceId, quantity: 3],
                [seatTypeId: qconTutorialsId, quantity: 1],
        ]
)

waitForUser()

def paymentId = generateId()
makePayment(paymentId, qconOrderId)

waitForUser()

def createConference(String conferenceId, String name, String location) {
    println "Create Conference: $name"

    def conference = [
            id      : conferenceId,
            name    : name,
            location: location
    ]

    given()
            .port(MANAGEMENT_PORT)
            .body(toJson(conference))
            .post("/conferences")
            .then()
            .assertThat().statusCode(HttpStatus.SC_CREATED)
            .extract().header(HttpHeaders.LOCATION)
}

def createSeatType(String seatTypeId, String type, int quantity, int priceAmount, String priceCurrency, String conferenceHref) {
    println "Create SeatType: $type"

    def seatType = [
            id           : seatTypeId,
            type         : type,
            quantity     : quantity,
            priceAmount  : priceAmount,
            priceCurrency: priceCurrency,
            conference   : conferenceHref
    ]

    given()
            .port(MANAGEMENT_PORT)
            .body(toJson(seatType))
            .post("/seatTypes")
            .then()
            .assertThat().statusCode(HttpStatus.SC_CREATED)
            .extract().header(HttpHeaders.LOCATION)
}

def registerToConference(String orderId, String conferenceId, def seats) {
    println "Register to conference: $conferenceId, $seats"

    def registerToConference = [
            orderId     : orderId,
            conferenceId: conferenceId,
            seats       : seats
    ]

    given()
            .port(REGISTRATIONS_PORT)
            .body(toJson(registerToConference))
            .post("/registrations/registerToConference")
            .then()
            .assertThat().statusCode(HttpStatus.SC_CREATED)
}

def makePayment(String paymentId, String orderId) {
    println "Make payment for order: $orderId"

    def makePayment = [
            paymentId: paymentId,
            orderId: orderId
    ]

    given()
            .port(PAYMENTS_PORT)
            .body(toJson(makePayment))
            .post("/payments/makePayment")
            .then()
            .assertThat().statusCode(HttpStatus.SC_CREATED)
}