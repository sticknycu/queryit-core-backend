package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto


interface MiniMarketService {
    fun findMiniMarketById(id: Long): ResponseEntity<MiniMarketDto>

    fun getMiniMarkets(): ResponseEntity<List<MiniMarketDto>>

    fun addMiniMarket(miniMarketDto: MiniMarketDto): ResponseEntity<Long>

    fun getMiniMarketByName(name: String): ResponseEntity<MiniMarketDto>

    fun deleteMiniMarketById(id: Long): ResponseEntity<Long>

    fun updateMiniMarket(miniMarketDto: MiniMarketDto): ResponseEntity<MiniMarketDto>
}