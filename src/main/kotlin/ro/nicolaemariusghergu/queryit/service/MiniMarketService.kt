package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto

interface MiniMarketService {
    open fun findMiniMarketById(id: Long?): ResponseEntity<MiniMarketDto?>?
    open fun getMiniMarkets(): ResponseEntity<MutableList<MiniMarketDto?>?>?
    open fun addMiniMarket(miniMarketDto: MiniMarketDto?): ResponseEntity<Long?>?
    open fun getMiniMarketByName(name: String?): ResponseEntity<MiniMarketDto?>?
    open fun deleteMiniMarketById(id: Long?): ResponseEntity<Long?>?
    open fun updateMiniMarket(miniMarketDto: MiniMarketDto?): ResponseEntity<MiniMarketDto?>?
}