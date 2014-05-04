package example.conference.client

import com.jayway.restassured.http.ContentType
import groovy.json.JsonBuilder
import org.apache.http.HttpStatus

import static com.jayway.restassured.RestAssured.given

def MANAGEMENT_PORT = 18080;
def json = new JsonBuilder()

// create QCon conference
given()
        .port(MANAGEMENT_PORT)
        .body(toJson(qconNyConference).toString())
        .contentType(ContentType.JSON)
        .post("/conferences")
        .then()
        .assertThat().statusCode(HttpStatus.SC_CREATED)

def getQconNyConference() {
    [
            id       : generateId(),
            name     : 'QCon 2014 NewYork',
            seatTypes:
                    [
                            [
                                    name         : 'Conference',
                                    quantity     : 800,
                                    priceAmout   : 1600,
                                    priceCurrency: 'USD'
                            ],
                            [
                                    name         : 'Tutorials',
                                    quantity     : 200,
                                    priceAmout   : 300,
                                    priceCurrency: 'USD'
                            ]
                    ]
    ]
}

def toJson(Object object) {
    new JsonBuilder(object).toString()
}

def generateId() {
    UUID.randomUUID().toString()
}

