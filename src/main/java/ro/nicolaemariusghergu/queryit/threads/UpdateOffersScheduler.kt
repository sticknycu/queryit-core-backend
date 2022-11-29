package ro.nicolaemariusghergu.queryit.threads

import lombok.SneakyThrows
import lombok.extern.slf4j.Slf4j
import org.springframework.scheduling.annotation.Scheduled
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.service.PromotionService
import java.util.function.Consumer

@Slf4j //@Configuration
//@EnableScheduling

class UpdateOffersScheduler(private val promotionService: PromotionService?) {
    @SneakyThrows
    @Scheduled(fixedRate = 1000)
    fun run() {
        promotionService.getPromotions().body
                .forEach(Consumer { promotion: PromotionDto? ->
                    val expirePromotionTime = promotion.getExpireDate()
                    val currentTime = System.currentTimeMillis()
                    //Instant instant = Instant.ofEpochMilli(expirePromotionTime);
                    //LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    //log.info("Expire Local Date time of offer " + promotion.getName() + " is " + localDateTime) ;
                    if (currentTime >= expirePromotionTime) {
                        promotionService.deletePromotionById(promotion.getId())
                    }
                })
    }
}