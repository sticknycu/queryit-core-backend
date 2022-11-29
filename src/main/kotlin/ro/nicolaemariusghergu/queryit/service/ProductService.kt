package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.ProductDto

interface ProductService {
    fun getProducts(): ResponseEntity<MutableList<ProductDto>>
}