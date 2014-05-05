package example.conference.client

import com.jayway.restassured.RestAssured
import com.jayway.restassured.http.ContentType
import org.apache.http.HttpStatus

import static com.jayway.restassured.RestAssured.given
import static example.conference.client.ConferenceClientUtils.generateId
import static example.conference.client.ConferenceClientUtils.toJson

RestAssured.port = 18080;
RestAssured.requestContentType(ContentType.JSON)

// create QCon conference
given()
        .body(toJson(qconConference))
        .post("/conferences")
        .then()
        .assertThat().statusCode(HttpStatus.SC_CREATED)

// create Devoxx conference
given()
        .body(toJson(devoxxConference))
        .post("/conferences")
        .then()
        .assertThat().statusCode(HttpStatus.SC_CREATED)

def getQconConference() {
    [
            id       : generateId(),
            name     : 'QCon 2014',
            location : 'New York',
            seatTypes:
                    [
                            [
                                    type         : 'Conference',
                                    quantity     : 800,
                                    priceAmount   : 1600,
                                    priceCurrency: 'USD'
                            ],
                            [
                                    type         : 'Tutorials',
                                    quantity     : 200,
                                    priceAmount   : 300,
                                    priceCurrency: 'USD'
                            ]
                    ]
    ]
}

def getDevoxxConference() {
    [
            id       : generateId(),
            name     : 'Devoxx 2014',
            location : 'Antwerp',
            seatTypes:
                    [
                            [
                                    type         : 'Conference',
                                    quantity     : 1500,
                                    priceAmount   : 300,
                                    priceCurrency: 'EUR'
                            ]
                    ]
    ]
}
