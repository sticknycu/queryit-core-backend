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
    override fun findMiniMarketById(id: Long): ResponseEntity<MiniMarketDto> {
        return ResponseEntity.ok(miniMarketRepository.findById(id).stream()
                .map { minimarket: MiniMarket -> MiniMarketMapper.INSTANCE.miniMarketToMiniMarketDto(minimarket) }
                .findFirst()
                .orElseThrow { NoSuchElementException("MiniMarket does not exist!") }
        )
    }

    override fun getMiniMarkets(): ResponseEntity<List<MiniMarketDto>> {
        return ResponseEntity.ok(miniMarketRepository.findAll()
                .stream()
                .map { minimarket: MiniMarket -> MiniMarketMapper.INSTANCE.miniMarketToMiniMarketDto(minimarket) }
                .toList())
    }

    override fun addMiniMarket(miniMarketDto: MiniMarketDto): ResponseEntity<Long> {
        miniMarketRepository.save(MiniMarketMapper.INSTANCE.miniMarketDtoToMiniMarket(miniMarketDto))
        return ResponseEntity.ok(miniMarketDto.id)
    }

    override fun getMiniMarketByName(name: String): ResponseEntity<MiniMarketDto> {
        return ResponseEntity.ok(MiniMarketMapper.INSTANCE.miniMarketToMiniMarketDto(
                miniMarketRepository.findByName(name)
                        .orElseThrow { NoSuchElementException("MiniMarket cannot be found") }
        ))
    }

    override fun deleteMiniMarketById(id: Long): ResponseEntity<Long> {
        miniMarketRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateMiniMarket(miniMarketDto: MiniMarketDto): ResponseEntity<MiniMarketDto> {
        val modifiedMiniMarket: MiniMarket = miniMarketRepository.findById(miniMarketDto.id).stream()
                .map { minimarket: MiniMarket ->
                    minimarket.name = miniMarketDto.name
                    minimarket
                }
                .findAny()
                .orElseThrow { NoSuchElementException("MiniMarket has not been found") }
        miniMarketRepository.save(modifiedMiniMarket)
        return ResponseEntity.ok(MiniMarketMapper.INSTANCE.miniMarketToMiniMarketDto(modifiedMiniMarket))
    }
}