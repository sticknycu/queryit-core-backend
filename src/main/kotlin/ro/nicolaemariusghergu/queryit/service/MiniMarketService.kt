package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.MiniMarketDto

interface MiniMarketService {
    fun getMiniMarkets(): ResponseEntity<MutableList<MiniMarketDto>>
}