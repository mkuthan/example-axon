package example.conference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.conference.management.ConferenceCreated;
import example.conference.payments.MakePayment;
import example.conference.registrations.RegisterToConference;
import org.axonframework.domain.GenericEventMessage;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.eventhandling.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ApplicationTest {

    public static final String QCON_2014_NY = "QCON_2014_NY";
    public static final String DEVOXX_2014_BE = "QCON_2014_BE";

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EventBus eventBus;

    @Value("${application.axon.eventStore.baseDir}")
    private String axonFileStoreDirectory;

    private MockMvc mockMvc;

    @Before
    public void setup() throws IOException {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        deleteFileRecursively();
    }

    @Test
    public void end2End() throws Exception {
        // simulate conference.management BC
        publishEvent(new ConferenceCreated(QCON_2014_NY, 1200));
        publishEvent(new ConferenceCreated(DEVOXX_2014_BE, 3800));

        // register to QCon
        String qConOrderIdentifier = generateIdentifier();
        RegisterToConference registerToQCon = new RegisterToConference(qConOrderIdentifier, QCON_2014_NY, 30);

        mockMvc
                .perform(post("/registrations/registerToConference")
                        .content(toJson(registerToQCon))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        // pay for QCon
        String qConPaymentIdentifier = generateIdentifier();
        MakePayment makeQConPayment = new MakePayment(qConOrderIdentifier, qConPaymentIdentifier);

        mockMvc
                .perform(post("/payments/makePayment")
                        .content(toJson(makeQConPayment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    private String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not create JSON representation for " + object + ".", e);
        }
    }

    private String generateIdentifier() {
        return IdentifierFactory.getInstance().generateIdentifier();
    }

    private void publishEvent(Object event) {
        eventBus.publish(GenericEventMessage.asEventMessage(event));
    }

    private void deleteFileRecursively() throws IOException {
        Path directory = Paths.get(axonFileStoreDirectory);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
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
