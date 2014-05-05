package example.conference.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({
        "classpath:/example/conference/configuration/axon.xml",
        "classpath:/example/conference/configuration/rabbitmq.xml"
})
@ComponentScan
public class ConferenceConfiguration {
}
