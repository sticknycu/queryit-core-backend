package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto
import ro.nicolaemariusghergu.queryit.mapper.MiniMarketMapper
import ro.nicolaemariusghergu.queryit.model.MiniMarket
import ro.nicolaemariusghergu.queryit.repository.MiniMarketRepository
import ro.nicolaemariusghergu.queryit.service.MiniMarketService

@Service
class MiniMarketServiceImpl(private val miniMarketRepository: MiniMarketRepository) : MiniMarketService {

    override fun getMiniMarkets(): ResponseEntity<MutableList<MiniMarketDto>> {
        return ResponseEntity.ok(miniMarketRepository.findAll()
                .stream()
                .map { miniMarket: MiniMarket -> MiniMarketMapper.INSTANCE.miniMarketToMiniMarketDto(miniMarket) }
                .toList())
    }
}