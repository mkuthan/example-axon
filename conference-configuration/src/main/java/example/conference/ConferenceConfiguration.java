package example.conference;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:/axon.xml", "classpath:/rabbitmq.xml", "classpath:/aop.xml"})
public class ConferenceConfiguration {
}
