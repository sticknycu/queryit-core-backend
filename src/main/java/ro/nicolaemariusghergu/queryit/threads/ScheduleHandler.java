package ro.nicolaemariusghergu.queryit.threads;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

//@Slf4j
@Configuration
@EnableScheduling
public class ScheduleHandler {

    private final PromotionService promotionService;

    public ScheduleHandler(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Scheduled(fixedRate = 1000)
    public void run() {
        promotionService.getPromotions().getBody()
                .forEach(promotion -> {
                    long expirePromotionTime = promotion.getExpireDate();
                    long currentTime = System.currentTimeMillis();
                    //Instant instant = Instant.ofEpochMilli(expirePromotionTime);
                    //LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    //log.info("Expire Local Date time of offer " + promotion.getName() + " is " + localDateTime) ;
                    if (currentTime >= expirePromotionTime) {
                        promotionService.deletePromotionById(promotion.getId());
                    }
                });
    }
}
