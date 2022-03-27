package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.dto.PromotionDto;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

import java.util.List;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.FRONTEND_CORE_ADDRESS;

@CrossOrigin(FRONTEND_CORE_ADDRESS)
@RequestMapping("/api/promotions")
@Controller
public record PromotionController(PromotionService promotionService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<PromotionDto>> getPromotions() {
        return promotionService.getPromotions();
    }

    @GetMapping("/v1/{promotionId}")
    @ResponseBody
    public ResponseEntity<PromotionDto> getPromotionById(@PathVariable Long promotionId) {
        return promotionService.getPromotionById(promotionId);
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Long> addPromotion(@RequestBody PromotionDto promotionDto) {
        return promotionService.addPromotion(promotionDto);
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<PromotionDto> updatePromotion(@RequestBody PromotionDto promotionDto) {
        return promotionService.updatePromotion(promotionDto);
    }

    @DeleteMapping("/v1/{promotionId}")
    @ResponseBody
    public ResponseEntity<Long> deletePromotion(@PathVariable Long promotionId) {
        return promotionService.deletePromotionById(promotionId);
    }
}

