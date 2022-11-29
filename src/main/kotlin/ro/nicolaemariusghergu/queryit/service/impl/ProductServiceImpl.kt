package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.mapper.ProductMapper
import ro.nicolaemariusghergu.queryit.model.Product
import ro.nicolaemariusghergu.queryit.repository.ProductRepository
import ro.nicolaemariusghergu.queryit.service.ProductService

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {

    override fun getProducts(): ResponseEntity<MutableList<ProductDto>> {
        return ResponseEntity.ok(productRepository.findAll()
                .stream()
                .map { product: Product -> ProductMapper.INSTANCE.productToProductDto(product) }
                .toList())
    }
}