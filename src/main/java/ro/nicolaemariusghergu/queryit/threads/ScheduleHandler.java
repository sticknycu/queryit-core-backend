package ro.nicolaemariusghergu.queryit.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Configuration
@EnableScheduling
public class ScheduleHandler {

    @Autowired
    PromotionService promotionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleHandler.class);

    @Scheduled(fixedRate = 1000)
    public void run() {
        promotionService.findAll()
                .forEach(promotion -> {
                    long expirePromotionTime = promotion.getExpireDate();
                    Instant instant = Instant.ofEpochMilli(expirePromotionTime);
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    //LOGGER.info("Expire Local Date time of offer " + promotion.getName() + " is " + localDateTime) ;
                    if (System.currentTimeMillis() >= expirePromotionTime) {
                        promotionService.deleteById(promotion.getId());
                    }
                });
    }
}
