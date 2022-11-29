package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.TruckDto

interface TruckService {
    open fun findTruckById(id: Long?): ResponseEntity<TruckDto?>?
    open fun getTrucks(): ResponseEntity<MutableList<TruckDto?>?>?
    open fun addTruck(truckDto: TruckDto?): ResponseEntity<Long?>?
    open fun getTruckBySerialNumber(serialNumber: String?): ResponseEntity<TruckDto?>?
    open fun deleteTruckById(id: Long?): ResponseEntity<Long?>?
    open fun updateTruck(truckDto: TruckDto?): ResponseEntity<TruckDto?>?
}