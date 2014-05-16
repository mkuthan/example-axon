package example.conference.registrations.impl;

import example.conference.configuration.ConferenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@Import({ConferenceConfiguration.class})
@ImportResource("classpath:/registrations.xml")
@ComponentScan
public class RegistrationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationsApplication.class, args);
    }

}
