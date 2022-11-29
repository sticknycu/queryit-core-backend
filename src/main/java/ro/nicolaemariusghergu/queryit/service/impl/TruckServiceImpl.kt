package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.TruckDto
import ro.nicolaemariusghergu.queryit.mapper.TruckMapper
import ro.nicolaemariusghergu.queryit.model.Truck
import ro.nicolaemariusghergu.queryit.service.TruckService
import java.util.function.Function
import java.util.function.Supplier

@Service
class TruckServiceImpl : TruckService {
    override fun findTruckById(id: Long?): ResponseEntity<TruckDto?>? {
        return ResponseEntity.ok(truckRepository.findById(id).stream()
                .map<TruckDto?>(Function<Truck?, TruckDto?> { truck: Truck? -> TruckMapper.Companion.INSTANCE.truckToTruckDto(truck) })
                .findFirst()
                .orElseThrow<NoSuchElementException?>(
                        Supplier { NoSuchElementException("Truck does not exist!") })
        )
    }

    override fun getTrucks(): ResponseEntity<MutableList<TruckDto?>?>? {
        return ResponseEntity.ok(truckRepository.findAll()
                .stream()
                .map<TruckDto?>(Function<Truck?, TruckDto?> { truck: Truck? -> TruckMapper.Companion.INSTANCE.truckToTruckDto(truck) })
                .toList())
    }

    override fun addTruck(truckDto: TruckDto?): ResponseEntity<Long?>? {
        truckRepository.save<Truck?>(TruckMapper.Companion.INSTANCE.truckDtoToTruck(truckDto))
        return ResponseEntity.ok(truckDto.getId())
    }

    override fun getTruckBySerialNumber(serialNumber: String?): ResponseEntity<TruckDto?>? {
        return ResponseEntity.ok(TruckMapper.Companion.INSTANCE.truckToTruckDto(
                truckRepository.findBySerialNumber(serialNumber)
                        .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Truck cannot be found") })
        ))
    }

    override fun deleteTruckById(id: Long?): ResponseEntity<Long?>? {
        truckRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateTruck(truckDto: TruckDto?): ResponseEntity<TruckDto?>? {
        val modifiedTruck: TruckDto = truckRepository.findById(truckDto.getId()).stream()
                .map<TruckDto?>(Function<Truck?, TruckDto?> { truck: Truck? -> TruckMapper.Companion.INSTANCE.truckToTruckDto(truck) })
                .map<TruckDto?>(Function { updatedTruck: TruckDto? ->
                    updatedTruck.setSerialNumber(truckDto.getSerialNumber())
                    updatedTruck
                })
                .findAny()
                .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Truck has not been found") }
                )
        truckRepository.save<Truck?>(
                TruckMapper.Companion.INSTANCE.truckDtoToTruck(modifiedTruck))
        return ResponseEntity.ok(modifiedTruck)
    }
}