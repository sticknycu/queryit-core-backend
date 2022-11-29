package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.service.PromotionService

@RequestMapping("/api/promotions")
@Controller
class PromotionController(private val promotionService: PromotionService) {
    @GetMapping("/v1")
    @ResponseBody
    fun getPromotions(): ResponseEntity<List<PromotionDto>> {
        return promotionService.getPromotions()
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addPromotion(@RequestBody promotionDto: PromotionDto): ResponseEntity<Long> {
        return promotionService.addPromotion(promotionDto)
    }

    @DeleteMapping("/v1/{promotionId}")
    @ResponseBody
    fun deletePromotion(@PathVariable promotionId: Long): ResponseEntity<Long> {
        return promotionService.deletePromotionById(promotionId)
    }
}