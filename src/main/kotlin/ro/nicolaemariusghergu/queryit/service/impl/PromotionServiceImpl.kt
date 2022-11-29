package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.mapper.PromotionMapper
import ro.nicolaemariusghergu.queryit.model.Promotion
import ro.nicolaemariusghergu.queryit.repository.PromotionRepository
import ro.nicolaemariusghergu.queryit.service.PromotionService

@Service
class PromotionServiceImpl(private val promotionRepository: PromotionRepository) : PromotionService {

    override fun getPromotions(): ResponseEntity<MutableList<PromotionDto>> {
        return ResponseEntity.ok(promotionRepository.findAll().stream()
                .map { promotion: Promotion -> PromotionMapper.INSTANCE.promotionToPromotionDto(promotion) }
                .toList())
    }

    override fun deletePromotionById(id: Long): ResponseEntity<Long> {
        promotionRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }
}