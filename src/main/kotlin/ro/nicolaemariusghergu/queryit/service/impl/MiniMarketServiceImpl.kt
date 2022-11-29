package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto
import ro.nicolaemariusghergu.queryit.mapper.MiniMarketMapper
import ro.nicolaemariusghergu.queryit.model.MiniMarket
import ro.nicolaemariusghergu.queryit.service.MiniMarketService
import java.util.function.Function
import java.util.function.Supplier

@Service
class MiniMarketServiceImpl : MiniMarketService {
    override fun findMiniMarketById(id: Long?): ResponseEntity<MiniMarketDto?>? {
        return ResponseEntity.ok(miniMarketRepository.findById(id).stream()
                .map<MiniMarketDto?>(Function<MiniMarket?, MiniMarketDto?> { miniMarket: MiniMarket? -> MiniMarketMapper.Companion.INSTANCE.miniMarketToMiniMarketDto(miniMarket) })
                .findFirst()
                .orElseThrow<NoSuchElementException?>(
                        Supplier { NoSuchElementException("MiniMarket does not exist!") })
        )
    }

    override fun getMiniMarkets(): ResponseEntity<MutableList<MiniMarketDto?>?>? {
        return ResponseEntity.ok(miniMarketRepository.findAll()
                .stream()
                .map<MiniMarketDto?>(Function<MiniMarket?, MiniMarketDto?> { miniMarket: MiniMarket? -> MiniMarketMapper.Companion.INSTANCE.miniMarketToMiniMarketDto(miniMarket) })
                .toList())
    }

    override fun addMiniMarket(miniMarketDto: MiniMarketDto?): ResponseEntity<Long?>? {
        miniMarketRepository.save<MiniMarket?>(MiniMarketMapper.Companion.INSTANCE.miniMarketDtoToMiniMarket(miniMarketDto))
        return ResponseEntity.ok(miniMarketDto.getId())
    }

    override fun getMiniMarketByName(name: String?): ResponseEntity<MiniMarketDto?>? {
        return ResponseEntity.ok(MiniMarketMapper.Companion.INSTANCE.miniMarketToMiniMarketDto(
                miniMarketRepository.findByName(name)
                        .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("MiniMarket cannot be found") })
        ))
    }

    override fun deleteMiniMarketById(id: Long?): ResponseEntity<Long?>? {
        miniMarketRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateMiniMarket(miniMarketDto: MiniMarketDto?): ResponseEntity<MiniMarketDto?>? {
        val modifiedMiniMarket: MiniMarketDto = miniMarketRepository.findById(miniMarketDto.getId()).stream()
                .map<MiniMarketDto?>(Function<MiniMarket?, MiniMarketDto?> { miniMarket: MiniMarket? -> MiniMarketMapper.Companion.INSTANCE.miniMarketToMiniMarketDto(miniMarket) })
                .map<MiniMarketDto?>(Function { updatedMiniMarket: MiniMarketDto? ->
                    updatedMiniMarket.setName(miniMarketDto.getName())
                    updatedMiniMarket
                })
                .findAny()
                .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("MiniMarket has not been found") }
                )
        miniMarketRepository.save<MiniMarket?>(
                MiniMarketMapper.Companion.INSTANCE.miniMarketDtoToMiniMarket(modifiedMiniMarket))
        return ResponseEntity.ok(modifiedMiniMarket)
    }
}