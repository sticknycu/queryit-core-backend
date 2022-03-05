package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.model.Promotion;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

import java.util.List;
import java.util.Optional;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.LOCALHOST;

@CrossOrigin(LOCALHOST)
@RequestMapping("/v1/promotions")
@Controller
public record PromotionController(PromotionService promotionService) {

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Promotion>> getPromotions() {
        return new ResponseEntity<>(promotionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{promotionId}")
    @ResponseBody
    public ResponseEntity<Optional<Promotion>> getPromotionById(@PathVariable Long promotionId) {
        return new ResponseEntity<>(promotionService.findById(promotionId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Promotion> addPromotion(@RequestBody Promotion promotion) {
        return new ResponseEntity<>(promotionService.save(promotion), HttpStatus.OK);
    }

    @PutMapping("/{promotionId}")
    @ResponseBody
    public ResponseEntity<Promotion> updatePromotion(@RequestBody Promotion promotion, @PathVariable Long promotionId) {
        Optional<Promotion> optionalPromotion = promotionService.findById(promotionId);

        if (optionalPromotion.isPresent()) {
            Promotion oldPromotion = optionalPromotion.get();
            oldPromotion.setName(promotion.getName());
            oldPromotion.setDescription(promotion.getDescription());

            promotionService.save(oldPromotion);

            return new ResponseEntity<>(oldPromotion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{promotionId}")
    @ResponseBody
    public ResponseEntity<Object> deleteProduct(@PathVariable Long promotionId) {
        promotionService.deleteById(promotionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

