package ro.nicolaemariusghergu.queryit.threads

import org.springframework.scheduling.annotation.Scheduled
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.service.PromotionService
import java.util.function.Consumer

//@Configuration
//@EnableScheduling
open class UpdateOffersScheduler(private val promotionService: PromotionService) {

    @Scheduled(fixedRate = 1000)
    fun run() {
        promotionService.getPromotions()!!.body
                ?.forEach(Consumer { promotion: PromotionDto? ->
                    val expirePromotionTime = promotion!!.expireDate
                    val currentTime = System.currentTimeMillis()
                    //Instant instant = Instant.ofEpochMilli(expirePromotionTime);
                    //LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    //log.info("Expire Local Date time of offer " + promotion.getName() + " is " + localDateTime) ;
                    if (currentTime >= expirePromotionTime) {
                        promotionService.deletePromotionById(promotion.id)
                    }
                })
    }
}