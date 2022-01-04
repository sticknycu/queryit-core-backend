package ro.nicolaemariusghergu.queryit.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.nicolaemariusghergu.queryit.model.Promotions;
import ro.nicolaemariusghergu.queryit.service.data.PromotionsService;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@Controller
public record PromotionsController(PromotionsService promotionsService) {

    @GetMapping("/promotions")
    @ResponseBody
    public ResponseEntity<List<Promotions>> getPromotions() {
        return new ResponseEntity<>(promotionsService.findAll(), HttpStatus.OK);
    }
}
