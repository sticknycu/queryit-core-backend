package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.mapper.PromotionMapper
import ro.nicolaemariusghergu.queryit.model.Promotion
import ro.nicolaemariusghergu.queryit.service.PromotionService
import java.util.function.Function
import java.util.function.Supplier

@Service
class PromotionServiceImpl : PromotionService {
    override fun getPromotionById(id: Long?): ResponseEntity<PromotionDto?>? {
        return ResponseEntity.ok(promotionRepository.findById(id)
                .map<PromotionDto?>(Function<Promotion?, PromotionDto?> { promotion: Promotion? -> PromotionMapper.Companion.INSTANCE.promotionToPromotionDto(promotion) })
                .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Promotion has not been found") })
        )
    }

    override fun getPromotions(): ResponseEntity<MutableList<PromotionDto?>?>? {
        return ResponseEntity.ok(promotionRepository.findAll().stream()
                .map<PromotionDto?>(Function<Promotion?, PromotionDto?> { promotion: Promotion? -> PromotionMapper.Companion.INSTANCE.promotionToPromotionDto(promotion) })
                .toList())
    }

    override fun getPromotionsByName(name: String?): ResponseEntity<MutableList<PromotionDto?>?>? {
        return ResponseEntity.ok(promotionRepository.findAllByName(name).stream()
                .map<PromotionDto?>(Function<Promotion?, PromotionDto?> { promotion: Promotion? -> PromotionMapper.Companion.INSTANCE.promotionToPromotionDto(promotion) })
                .toList())
    }

    override fun updatePromotion(promotionDto: PromotionDto?): ResponseEntity<PromotionDto?>? {
        val promotionAlreadyExist: PromotionDto = promotionRepository.findById(promotionDto.getId())
                .map<PromotionDto?>(Function<Promotion?, PromotionDto?> { promotion: Promotion? -> PromotionMapper.Companion.INSTANCE.promotionToPromotionDto(promotion) })
                .map<PromotionDto?>(Function { updatedPromotion: PromotionDto? ->
                    updatedPromotion.setQuantityNeeded(promotionDto.getQuantityNeeded())
                    updatedPromotion
                })
                .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Promotion has not been found") })
        promotionRepository.save<Promotion?>(PromotionMapper.Companion.INSTANCE
                .promotionDtoToPromotion(promotionAlreadyExist))
        return ResponseEntity.ok(promotionAlreadyExist)
    }

    override fun addPromotion(promotionDto: PromotionDto?): ResponseEntity<Long?>? {
        promotionRepository.save<Promotion?>(
                PromotionMapper.Companion.INSTANCE.promotionDtoToPromotion(promotionDto))
        return ResponseEntity.ok(promotionDto.getId())
    }

    override fun deletePromotionById(id: Long?): ResponseEntity<Long?>? {
        promotionRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }
}