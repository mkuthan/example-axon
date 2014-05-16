package example.conference.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/example/conference/configuration/configuration.properties")
@ImportResource({
        "classpath:/example/conference/configuration/axon.xml",
        "classpath:/example/conference/configuration/rabbit.xml"
})
public class ConferenceConfiguration {
    @Bean
    public EventAuditListener eventAuditListener() {
        return new EventAuditListener();
    }
}