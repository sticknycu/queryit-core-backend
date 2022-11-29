package ro.nicolaemariusghergu.queryit

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["ro.nicolaemariusghergu.queryit"])
open class BackEndApplication {
    val FRONTEND_CORE_ADDRESS: String? = "http://localhost:4000"

    fun main(args: Array<String>) {
        runApplication<BackEndApplication>(*args)
    }
}