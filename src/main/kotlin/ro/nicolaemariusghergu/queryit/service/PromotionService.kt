package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.PromotionDto

interface PromotionService {
    fun getPromotions(): ResponseEntity<MutableList<PromotionDto>>
    fun deletePromotionById(id: Long): ResponseEntity<Long>
}