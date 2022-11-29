package ro.nicolaemariusghergu.queryit.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto;

import java.util.List;

public interface MiniMarketService {

    ResponseEntity<MiniMarketDto> findMiniMarketById(Long id);

    ResponseEntity<List<MiniMarketDto>> getMiniMarkets();

    ResponseEntity<Long> addMiniMarket(MiniMarketDto miniMarketDto);

    ResponseEntity<MiniMarketDto> getMiniMarketByName(String name);

    ResponseEntity<Long> deleteMiniMarketById(Long id);

    ResponseEntity<MiniMarketDto> updateMiniMarket(MiniMarketDto miniMarketDto);
}
