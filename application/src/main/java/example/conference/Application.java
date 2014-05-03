package example.conference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

@Configuration
@ImportResource({"classpath:/axon.xml", "classpath:/rabbitmq.xml", "classpath:/aop.xml"})
@EnableAutoConfiguration
@ComponentScan
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            logger.info("Bean: {}", beanName);
        }
    }

}
