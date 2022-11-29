package ro.nicolaemariusghergu.queryit.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ro.nicolaemariusghergu.queryit.BackEndApplication
import ro.nicolaemariusghergu.queryit.dto.ProductDto

@RequestMapping("/api/products")
@CrossOrigin(BackEndApplication.FRONTEND_CORE_ADDRESS)
@Controller
class ProductController {
    @GetMapping("/v1")
    @ResponseBody
    fun getProducts(): ResponseEntity<MutableList<ProductDto?>?>? {
        return productService.getProducts()
    }

    @GetMapping("/v1/proxy-products/{categoryId}")
    fun getProxyProducts(@PathVariable categoryId: Long?): ResponseEntity<MutableSet<ProductDto?>?>? {
        return productService.getProxyProducts(categoryId)
    }

    @GetMapping("/v1/productsByCategoryId/{categoryId}")
    @ResponseBody
    fun getProductsByCategoryId(@PathVariable categoryId: Long?): ResponseEntity<MutableList<ProductDto?>?>? {
        return productService.getProductsByCategoryId(categoryId)
    }

    @GetMapping("/v1/{productId}")
    @ResponseBody
    fun getProductById(@PathVariable productId: Long?): ResponseEntity<ProductDto?>? {
        return productService.findProductById(productId)
    }

    @PostMapping("/v1")
    @ResponseBody
    fun addProduct(@RequestBody productDto: ProductDto?): ResponseEntity<Long?>? {
        return productService.addProduct(productDto)
    }

    @PatchMapping("/v1")
    @ResponseBody
    fun updateProduct(@RequestBody productDto: ProductDto?): ResponseEntity<ProductDto?>? {
        return productService.updateProduct(productDto)
    }

    @DeleteMapping("/v1/{productId}")
    @ResponseBody
    fun deleteProduct(@PathVariable productId: Long?): ResponseEntity<Long?>? {
        return productService.deleteProductById(productId)
    }
}