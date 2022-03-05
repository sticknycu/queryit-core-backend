package ro.nicolaemariusghergu.queryit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.LOCALHOST;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient
                .builder()
                .baseUrl(LOCALHOST);
    }
}
