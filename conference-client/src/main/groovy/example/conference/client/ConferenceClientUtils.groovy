package example.conference.client

import groovy.json.JsonBuilder

class ConferenceClientUtils {

    static def toJson(Object object) {
        new JsonBuilder(object).toPrettyString()
    }

    static def generateId() {
        UUID.randomUUID().toString();
    }
}
