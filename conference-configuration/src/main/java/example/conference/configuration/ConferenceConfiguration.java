package example.conference.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/example/conference/configuration/configuration.properties")
@ImportResource("classpath:/example/conference/configuration/configuration.xml")
public class ConferenceConfiguration {
    @Bean
    public EventAuditListener eventAuditListener() {
        return new EventAuditListener();
    }
}