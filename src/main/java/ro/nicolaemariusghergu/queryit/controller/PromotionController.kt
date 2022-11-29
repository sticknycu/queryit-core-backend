package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.PromotionDto

@CrossOrigin(BackEndApplication.FRONTEND_CORE_ADDRESS)
@RequestMapping("/api/promotions")
@Controller
class PromotionController {
    @GetMapping("/v1")
    @ResponseBody
    fun getPromotions(): ResponseEntity<MutableList<PromotionDto?>?>? {
        return promotionService.getPromotions()
    }

    @GetMapping("/v1/{promotionId}")
    @ResponseBody
    fun getPromotionById(@PathVariable promotionId: Long?): ResponseEntity<PromotionDto?>? {
        return promotionService.getPromotionById(promotionId)
    }

    @GetMapping("/v1/promotion-name")
    @ResponseBody
    fun getPromotionByName(@RequestBody promotionDto: PromotionDto?): ResponseEntity<MutableList<PromotionDto?>?>? {
        return promotionService.getPromotionsByName(promotionDto.getName())
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addPromotion(@RequestBody promotionDto: PromotionDto?): ResponseEntity<Long?>? {
        return promotionService.addPromotion(promotionDto)
    }

    @PatchMapping("/v1")
    @ResponseBody
    fun updatePromotion(@RequestBody promotionDto: PromotionDto?): ResponseEntity<PromotionDto?>? {
        return promotionService.updatePromotion(promotionDto)
    }

    @DeleteMapping("/v1/{promotionId}")
    @ResponseBody
    fun deletePromotion(@PathVariable promotionId: Long?): ResponseEntity<Long?>? {
        return promotionService.deletePromotionById(promotionId)
    }
}