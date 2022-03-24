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

@CrossOrigin(LOCAL_HOST_ADDRESS)
@Controller
public record ProductController(ProductService productService, CategoryService categoryService) {

    @GetMapping("/v1/products")
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

    @GetMapping("/v1/products/{productId}")
    @ResponseBody
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @PostMapping("/v1/products")
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping("/v1/products/{productId}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long productId) {
        Optional<Product> optionalProduct = productService.findById(productId);

        if (optionalProduct.isPresent()) {
            Product oldProduct = optionalProduct.get();
            oldProduct.setName(product.getName());
            oldProduct.setQuantity(product.getQuantity());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setIconUrl(product.getIconUrl());
            oldProduct.setCategory(product.getCategory());
            oldProduct.setManufacturer(product.getManufacturer());
            oldProduct.setMiniMarket(product.getMiniMarket());
            oldProduct.setPromotion(product.getPromotion());

            productService.save(oldProduct);

            return new ResponseEntity<>(oldProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/v1/products/{productId}")
    @ResponseBody
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
