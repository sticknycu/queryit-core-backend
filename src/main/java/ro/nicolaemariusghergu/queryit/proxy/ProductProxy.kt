package ro.nicolaemariusghergu.queryit.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.nicolaemariusghergu.queryit.dto.CategoryDto;
import ro.nicolaemariusghergu.queryit.dto.ProductDto;
import ro.nicolaemariusghergu.queryit.dto.PromotionDto;
import ro.nicolaemariusghergu.queryit.model.Manufacturer;

import java.util.Set;

@FeignClient(name="products", url = "http://localhost:9092")
public interface ProductProxy {

    @GetMapping("/api/download/v1/mega-image/products/{categoryId}")
    ResponseEntity<Set<ProductDto>> getProductsFromMegaImageByCategory(@PathVariable Long categoryId);

    @GetMapping("/api/download/v1/mega-image/products")
    ResponseEntity<Set<ProductDto>> getProductsFromMegaImage();

    @GetMapping("/api/download/v1/mega-image/promotions")
    ResponseEntity<Set<PromotionDto>> getPromotionsFromMegaImage();

    @GetMapping("/api/download/v1/mega-image/product-promotions")
    ResponseEntity<Set<ProductDto>> getProductsWithPromotionFromMegaImage();

    @GetMapping("/api/download/v1/mega-image/categories")
    ResponseEntity<Set<CategoryDto>> getCategoriesFromMegaImage();

    @GetMapping("/api/download/v1/mega-image/manufacturers")
    ResponseEntity<Set<Manufacturer>> getManufacturersFromMegaImage();
}
