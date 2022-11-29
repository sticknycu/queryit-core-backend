package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Truck

@Transactional
interface TruckRepository : JpaRepository<Truck, Long>