package ro.nicolaemariusghergu.queryit.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["ro.nicolaemariusghergu.queryit.gateway"])
class OpenFeignConfig 