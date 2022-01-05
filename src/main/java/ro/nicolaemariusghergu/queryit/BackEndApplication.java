package ro.nicolaemariusghergu.queryit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ro.nicolaemariusghergu.queryit.service.data.ProductService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.nicolaemariusghergu.queryit")
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

}
