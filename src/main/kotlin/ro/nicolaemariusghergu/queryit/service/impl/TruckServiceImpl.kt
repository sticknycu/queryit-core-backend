package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.TruckDto
import ro.nicolaemariusghergu.queryit.mapper.TruckMapper
import ro.nicolaemariusghergu.queryit.model.Truck
import ro.nicolaemariusghergu.queryit.repository.TruckRepository
import ro.nicolaemariusghergu.queryit.service.TruckService

@Service
class TruckServiceImpl(private val truckRepository: TruckRepository) : TruckService {

    override fun getTrucks(): ResponseEntity<List<TruckDto>> {
        return ResponseEntity.ok(truckRepository.findAll()
                .stream()
                .map { truck: Truck -> TruckMapper.INSTANCE.truckToTruckDto(truck) }
                .toList())
    }

    override fun addTruck(truckDto: TruckDto): ResponseEntity<Long> {
        truckRepository.save(TruckMapper.INSTANCE.toDto(truckDto))
        return ResponseEntity.ok(truckDto.id)
    }

    override fun deleteTruckById(id: Long): ResponseEntity<Long> {
        truckRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }
}