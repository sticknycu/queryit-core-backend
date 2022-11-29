package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.PromotionDto;
import ro.nicolaemariusghergu.queryit.mapper.PromotionMapper;
import ro.nicolaemariusghergu.queryit.repository.PromotionRepository;
import ro.nicolaemariusghergu.queryit.service.PromotionService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public record PromotionServiceImpl(PromotionRepository promotionRepository) implements PromotionService {

    @Override
    public ResponseEntity<PromotionDto> getPromotionById(Long id) {
        return ResponseEntity.ok(promotionRepository.findById(id)
                .map(PromotionMapper.INSTANCE::promotionToPromotionDto)
                .orElseThrow(() ->
                        new NoSuchElementException("Promotion has not been found"))
        );
    }

    @Override
    public ResponseEntity<List<PromotionDto>> getPromotions() {
        return ResponseEntity.ok(promotionRepository.findAll().stream()
                .map(PromotionMapper.INSTANCE::promotionToPromotionDto)
                .toList());
    }

    @Override
    public ResponseEntity<List<PromotionDto>> getPromotionsByName(String name) {
        return ResponseEntity.ok(promotionRepository.findAllByName(name).stream()
                .map(PromotionMapper.INSTANCE::promotionToPromotionDto)
                .toList());
    }

    @Override
    public ResponseEntity<PromotionDto> updatePromotion(PromotionDto promotionDto) {
        PromotionDto promotionAlreadyExist = promotionRepository.findById(promotionDto.getId())
                .map(PromotionMapper.INSTANCE::promotionToPromotionDto)
                .map(updatedPromotion -> {
                    updatedPromotion.setQuantityNeeded(promotionDto.getQuantityNeeded());
                    return updatedPromotion;
                })
                .orElseThrow(() ->
                        new NoSuchElementException("Promotion has not been found"));
        promotionRepository.save(PromotionMapper.INSTANCE
                .promotionDtoToPromotion(promotionAlreadyExist));
        return ResponseEntity.ok(promotionAlreadyExist);
    }

    @Override
    public ResponseEntity<Long> addPromotion(PromotionDto promotionDto) {
        promotionRepository.save(
                PromotionMapper.INSTANCE.promotionDtoToPromotion(promotionDto));
        return ResponseEntity.ok(promotionDto.getId());
    }

    @Override
    public ResponseEntity<Long> deletePromotionById(Long id) {
        promotionRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }


}
