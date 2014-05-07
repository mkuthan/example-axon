package example.conference.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

// TODO
@ConfigurationProperties(prefix = "application.configuration")
public class ConferenceProperties {

    private ConferenceProperties.Axon axon;

    private ConferenceProperties.Rabbit rabbit;

    public ConferenceProperties.Axon getAxon() {
        return axon;
    }

    public void setAxon(ConferenceProperties.Axon axon) {
        this.axon = axon;
    }

    public ConferenceProperties.Rabbit getRabbit() {
        return rabbit;
    }

    public void setRabbit(ConferenceProperties.Rabbit rabbit) {
        this.rabbit = rabbit;
    }

    public static class Axon {

        private String eventStoreBaseDir;

        public String getEventStoreBaseDir() {
            return eventStoreBaseDir;
        }

        public void setEventStoreBaseDir(String eventStoreBaseDir) {
            this.eventStoreBaseDir = eventStoreBaseDir;
        }
    }

    public static class Rabbit {

        private String host = "localhost";

        private int port = 5672;

        private String username = "guest";

        private String password = "guest";

        private String exchangeName = "Axon.EventBus";

        private String queueName;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getExchangeName() {
            return exchangeName;
        }

        public void setExchangeName(String exchangeName) {
            this.exchangeName = exchangeName;
        }

        public String getQueueName() {
            return queueName;
        }

        public void setQueueName(String queueName) {
            this.queueName = queueName;
        }
    }
}
