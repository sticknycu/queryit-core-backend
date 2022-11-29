package ro.nicolaemariusghergu.queryit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["ro.nicolaemariusghergu.queryit"])
open class BackEndApplication

fun main(args: Array<String>) {
    runApplication<BackEndApplication>(*args)
}
