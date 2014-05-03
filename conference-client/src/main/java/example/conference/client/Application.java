package example.conference.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import example.conference.management.impl.domain.Conference;
import example.conference.management.impl.domain.SeatType;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;

import static com.jayway.restassured.RestAssured.given;

public class Application {

    private static final String AXON_FILE_STORE_DIRECTORY = "/tmp/example-axon";

    private static final int MANAGEMENT_PORT = 18080;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        deleteFileRecursively(AXON_FILE_STORE_DIRECTORY);

        // create QCon conference
        Conference conference = new Conference(generateId(), "QCon 2014 NewYork");
        conference.addSeatType(new SeatType("Regular", 1200, toAmout(1500), "USD"));
        conference.addSeatType(new SeatType("Speaker", 50, toAmout(0), "USD"));

        given()
                .port(MANAGEMENT_PORT)
                .body(toJson(conference))
                .contentType(ContentType.JSON)
                .post("/conferences")
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED);
    }

    private static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not create JSON representation for " + object + ".", e);
        }
    }

    private static String generateId() {
        return UUID.randomUUID().toString();
    }

    private static BigDecimal toAmout(int amount) {
        return BigDecimal.valueOf(amount);
    }

    private static void deleteFileRecursively(String directory) throws IOException {
        Path directoryPath = Paths.get(directory);
        if (!directoryPath.toFile().exists()) {
            return;
        }
        Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
