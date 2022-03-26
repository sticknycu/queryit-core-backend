package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.model.Promotion;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

import java.util.List;
import java.util.Optional;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.LOCAL_HOST_ADDRESS;

@CrossOrigin(LOCAL_HOST_ADDRESS)
@RequestMapping("/api/promotions")
@Controller
public record PromotionController(PromotionService promotionService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<Promotion>> getPromotions() {
        return new ResponseEntity<>(promotionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/v1/{promotionId}")
    @ResponseBody
    public ResponseEntity<Optional<Promotion>> getPromotionById(@PathVariable Long promotionId) {
        return new ResponseEntity<>(promotionService.findById(promotionId), HttpStatus.OK);
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Promotion> addPromotion(@RequestBody Promotion promotion) {
        return new ResponseEntity<>(promotionService.save(promotion), HttpStatus.OK);
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<Promotion> updatePromotion(@RequestBody Promotion promotion) {
        Promotion updatedPromotion = promotionService.update(promotion);
        return new ResponseEntity<>(updatedPromotion, HttpStatus.OK);
    }

    @DeleteMapping("/v1/{promotionId}")
    @ResponseBody
    public ResponseEntity<Object> deleteProduct(@PathVariable Long promotionId) {
        promotionService.deleteById(promotionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

