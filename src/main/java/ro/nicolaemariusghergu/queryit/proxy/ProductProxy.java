package ro.nicolaemariusghergu.queryit.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.nicolaemariusghergu.queryit.model.Product;

import java.util.List;

@FeignClient(name="products", url = "http://localhost:9092")
@RequestMapping("/api/products")
public interface ProductProxy {

    @GetMapping("/api/download/v1/mega-image/{categoryId}")
    ResponseEntity<List<Product>> getProductsFromMegaImageByCategory(@PathVariable Long categoryId);

    @GetMapping("/api/download/v1/mega-image")
    ResponseEntity<List<Product>> getProductsFromMegaImage();
}
