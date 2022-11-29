package ro.nicolaemariusghergu.queryit.threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

@Slf4j
//@Configuration
//@EnableScheduling
public class UpdateOffersScheduler {

    private final PromotionService promotionService;

    public UpdateOffersScheduler(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @SneakyThrows
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
