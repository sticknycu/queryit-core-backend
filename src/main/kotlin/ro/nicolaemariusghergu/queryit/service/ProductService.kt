package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.ProductDto


interface ProductService {
    fun findProductById(id: Long): ResponseEntity<ProductDto>

    fun getProducts(): ResponseEntity<List<ProductDto>>

    fun addProduct(productDto: ProductDto): ResponseEntity<Long>

    fun getProductByName(name: String): ProductDto

    fun deleteProductById(id: Long): ResponseEntity<Long>

    fun updateProduct(productDto: ProductDto): ResponseEntity<ProductDto>

    fun getProxyProducts(categoryId: Long): ResponseEntity<Set<ProductDto>>

    fun getProductsByCategoryId(categoryId: Long): ResponseEntity<List<ProductDto>>
}