package example.conference;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({
        "classpath:/example/conference/aop.xml",
        "classpath:/example/conference/axon.xml",
        "classpath:/example/conference/rabbitmq.xml"
        })
public class ConferenceConfiguration {
}
