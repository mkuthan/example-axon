package example.conference.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({
        "classpath:/example/conference/configuration/axon.xml",
        "classpath:/example/conference/configuration/rabbit.xml"
})
@EnableConfigurationProperties
public class ConferenceConfiguration {

    @Bean
    public ConferenceProperties conferenceProperties() {
        return new ConferenceProperties();
    }

    @Bean
    public EventAuditListener eventAuditListener() {
        return new EventAuditListener();
    }
}