package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto;
import ro.nicolaemariusghergu.queryit.mapper.MiniMarketMapper;
import ro.nicolaemariusghergu.queryit.repository.MiniMarketRepository;
import ro.nicolaemariusghergu.queryit.service.MiniMarketService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public record MiniMarketServiceImpl(MiniMarketRepository miniMarketRepository) implements MiniMarketService {

    @Override
    public ResponseEntity<MiniMarketDto> findMiniMarketById(Long id) {
        return ResponseEntity.ok(miniMarketRepository.findById(id).stream()
                .map(MiniMarketMapper.INSTANCE::miniMarketToMiniMarketDto)
                .findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException("MiniMarket does not exist!"))
        );
    }

    @Override
    public ResponseEntity<List<MiniMarketDto>> getMiniMarkets() {
        return ResponseEntity.ok(miniMarketRepository.findAll()
                .stream()
                .map(MiniMarketMapper.INSTANCE::miniMarketToMiniMarketDto)
                .toList());
    }

    @Override
    public ResponseEntity<Long> addMiniMarket(MiniMarketDto miniMarketDto) {
        miniMarketRepository.save(MiniMarketMapper.INSTANCE.miniMarketDtoToMiniMarket(miniMarketDto));
        return ResponseEntity.ok(miniMarketDto.getId());
    }

    @Override
    public ResponseEntity<MiniMarketDto> getMiniMarketByName(String name) {
        return ResponseEntity.ok(MiniMarketMapper.INSTANCE.miniMarketToMiniMarketDto(
                miniMarketRepository.findByName(name)
                        .orElseThrow(() ->
                                new NoSuchElementException("MiniMarket cannot be found"))
        ));
    }

    @Override
    public ResponseEntity<Long> deleteMiniMarketById(Long id) {
        miniMarketRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<MiniMarketDto> updateMiniMarket(MiniMarketDto miniMarketDto) {
        MiniMarketDto modifiedMiniMarket = miniMarketRepository.findById(miniMarketDto.getId()).stream()
                .map(MiniMarketMapper.INSTANCE::miniMarketToMiniMarketDto)
                .map(updatedMiniMarket -> {
                    updatedMiniMarket.setName(miniMarketDto.getName());
                    return updatedMiniMarket;
                })
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("MiniMarket has not been found")
                );
        miniMarketRepository.save(
                MiniMarketMapper.INSTANCE.miniMarketDtoToMiniMarket(modifiedMiniMarket));
        return ResponseEntity.ok(modifiedMiniMarket);
    }
}
