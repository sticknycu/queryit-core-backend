package ro.nicolaemariusghergu.queryit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.nicolaemariusghergu.queryit.model.Product;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:60028")
@Controller
public record ProductController(ProductService productService) {

    @GetMapping("/products")
    @ResponseBody
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    @ResponseBody
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
    }

    @PostMapping("/products")
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping("/products/{productId}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long productId) {
        Optional<Product> optionalProduct = productService.findById(productId);

        if (optionalProduct.isPresent()) {
            Product oldProduct = optionalProduct.get();

            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setIconUrl(product.getIconUrl());
            oldProduct.setCategory(product.getCategory());
            oldProduct.setManufacturer(product.getManufacturer());
            oldProduct.setMiniMarket(product.getMiniMarket());
            oldProduct.setPromotion(product.getPromotion());

            return new ResponseEntity<>(oldProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{productId}")
    @ResponseBody
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) {
        productService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
