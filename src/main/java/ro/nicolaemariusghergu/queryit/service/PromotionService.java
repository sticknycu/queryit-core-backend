package ro.nicolaemariusghergu.queryit.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.queryit.dto.PromotionDto;

import java.util.List;

public interface PromotionService {

    ResponseEntity<PromotionDto> getPromotionById(Long id);

    ResponseEntity<List<PromotionDto>> getPromotions();

    ResponseEntity<List<PromotionDto>> getPromotionsByName(String name);

    ResponseEntity<PromotionDto> updatePromotion(PromotionDto promotionDto);

    ResponseEntity<Long> addPromotion(PromotionDto promotionDto);

    ResponseEntity<Long> deletePromotionById(Long id);

}
