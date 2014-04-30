package example.conference;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import example.conference.management.ConferenceCreated;
import example.conference.registrations.RegisterToConference;
import org.axonframework.domain.GenericEventMessage;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.eventhandling.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.HeaderResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ApplicationTest {

    public static final String QCON_2014_NY  = "QCON_2014_NY";
    public static final String DEVOXX_2014_BE  = "QCON_2014_BE";

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private ObjectMapper objectMapper;

    @Autowired
    private EventBus eventBus;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
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
			.andDo(print())
			.andExpect(status().isCreated())
            .andExpect(header().string("Location", "/registrations/orders/" + qConOrderIdentifier));

        // register to Devoxx
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

}
