package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.PromotionDto

interface PromotionService {
    open fun getPromotionById(id: Long?): ResponseEntity<PromotionDto?>?
    open fun getPromotions(): ResponseEntity<MutableList<PromotionDto?>?>?
    open fun getPromotionsByName(name: String?): ResponseEntity<MutableList<PromotionDto?>?>?
    open fun updatePromotion(promotionDto: PromotionDto?): ResponseEntity<PromotionDto?>?
    open fun addPromotion(promotionDto: PromotionDto?): ResponseEntity<Long?>?
    open fun deletePromotionById(id: Long?): ResponseEntity<Long?>?
}