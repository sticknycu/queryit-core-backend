package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.dto.ProductDto;
import ro.nicolaemariusghergu.queryit.service.CategoryService;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.util.List;
import java.util.Set;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.FRONTEND_CORE_ADDRESS;

@RequestMapping("/api/products")
@CrossOrigin(FRONTEND_CORE_ADDRESS)
@Controller
public record ProductController(ProductService productService,
                                CategoryService categoryService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/v1/proxy-products/{categoryId}")
    public ResponseEntity<Set<ProductDto>> getProxyProducts(@PathVariable Long categoryId) {
        return productService.getProxyProducts(categoryId);
    }

    @GetMapping("/v1/productsByCategoryId/{categoryId}")
    @ResponseBody
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/v1/{productId}")
    @ResponseBody
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        return productService.findProductById(productId);
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Long> addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/v1/{productId}")
    @ResponseBody
    public ResponseEntity<Long> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProductById(productId);
    }
}
