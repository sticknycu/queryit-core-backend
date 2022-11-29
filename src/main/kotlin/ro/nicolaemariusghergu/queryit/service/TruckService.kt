package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.TruckDto


interface TruckService {

    fun getTrucks(): ResponseEntity<List<TruckDto>>

    fun addTruck(truckDto: TruckDto): ResponseEntity<Long>

    fun deleteTruckById(id: Long): ResponseEntity<Long>

}