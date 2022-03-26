package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.service.CategoryService;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.util.List;
import java.util.Optional;

import static ro.nicolaemariusghergu.queryit.BackEndApplication.LOCAL_HOST_ADDRESS;

@RequestMapping("/api/products")
@CrossOrigin(LOCAL_HOST_ADDRESS)
@Controller
public record ProductController(ProductService productService, CategoryService categoryService) {

    @GetMapping("/v1")
    @ResponseBody
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/v1/productsByCategoryId/{categoryId}")
    @ResponseBody
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId) {
        if (categoryService.findById(categoryId).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.findAllByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("/v1/{productId}")
    @ResponseBody
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @PostMapping("/v1")
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PatchMapping("/v1")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.update(product);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/v1/{productId}")
    @ResponseBody
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
