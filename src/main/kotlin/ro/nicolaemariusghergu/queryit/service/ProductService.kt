package ro.nicolaemariusghergu.queryit.service

import org.springframework.http.ResponseEntity
import ro.nicolaemariusghergu.queryit.dto.ProductDto

interface ProductService {
    open fun findProductById(id: Long?): ResponseEntity<ProductDto?>?
    open fun getProducts(): ResponseEntity<MutableList<ProductDto?>?>?
    open fun addProduct(productDto: ProductDto?): ResponseEntity<Long?>?
    open fun getProductByName(name: String?): ProductDto?
    open fun deleteProductById(id: Long?): ResponseEntity<Long?>?
    open fun updateProduct(productDto: ProductDto?): ResponseEntity<ProductDto?>?
    open fun getProxyProducts(categoryId: Long?): ResponseEntity<MutableSet<ProductDto?>?>?
    open fun getProductsByCategoryId(categoryId: Long?): ResponseEntity<MutableList<ProductDto?>?>?
}