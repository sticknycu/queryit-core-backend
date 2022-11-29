package ro.nicolaemariusghergu.queryit.gateway

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.dto.PromotionDto
import ro.nicolaemariusghergu.queryit.model.Manufacturer

@FeignClient(name = "products", url = "http://localhost:9092")
interface ProductProxy {
    @GetMapping("/api/download/v1/mega-image/products/{categoryId}")
    fun getProductsFromMegaImageByCategory(@PathVariable categoryId: Long): ResponseEntity<Set<ProductDto>>
    @GetMapping("/api/download/v1/mega-image/products")
    fun getProductsFromMegaImage(): ResponseEntity<Set<ProductDto>>
    @GetMapping("/api/download/v1/mega-image/promotions")
    fun getPromotionsFromMegaImage(): ResponseEntity<Set<PromotionDto>>
    @GetMapping("/api/download/v1/mega-image/product-promotions")
    fun getProductsWithPromotionFromMegaImage(): ResponseEntity<Set<ProductDto>>
    @GetMapping("/api/download/v1/mega-image/categories")
    fun getCategoriesFromMegaImage(): ResponseEntity<Set<CategoryDto>>
    @GetMapping("/api/download/v1/mega-image/manufacturers")
    fun getManufacturersFromMegaImage(): ResponseEntity<Set<Manufacturer>>
}