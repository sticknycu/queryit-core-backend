package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.TruckDto

interface TruckService {
    fun getTrucks(): ResponseEntity<MutableList<TruckDto>>
}