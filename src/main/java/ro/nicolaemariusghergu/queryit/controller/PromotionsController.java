package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.model.Promotion;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:60028")
@Controller
public record PromotionsController(PromotionService promotionService) {

    @GetMapping("/promotions")
    @ResponseBody
    public ResponseEntity<List<Promotion>> getPromotions() {
        return new ResponseEntity<>(promotionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/promotions/{promotionId}")
    @ResponseBody
    public ResponseEntity<Optional<Promotion>> getPromotionById(@PathVariable Long promotionId) {
        return new ResponseEntity<>(promotionService.findById(promotionId), HttpStatus.OK);
    }

    @PostMapping("/promotions")
    @ResponseBody
    public ResponseEntity<Promotion> addPromotion(@RequestBody Promotion promotion) {
        return new ResponseEntity<>(promotionService.save(promotion), HttpStatus.OK);
    }

    @PutMapping("/promotions/{promotionId}")
    @ResponseBody
    public ResponseEntity<Promotion> updatePromotion(@RequestBody Promotion promotion, @PathVariable Long promotionId) {
        Optional<Promotion> optionalPromotion = promotionService.findById(promotionId);

        if (optionalPromotion.isPresent()) {
            Promotion oldPromotion = optionalPromotion.get();

            oldPromotion.setName(promotion.getName());
            oldPromotion.setDescription(promotion.getDescription());

            return new ResponseEntity<>(oldPromotion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/promotions/{promotionId}")
    @ResponseBody
    public ResponseEntity<Object> deletePromotion(@PathVariable Long promotionId) {
        promotionService.deleteById(promotionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
