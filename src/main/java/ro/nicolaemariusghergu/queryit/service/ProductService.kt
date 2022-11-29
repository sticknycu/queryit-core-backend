package ro.nicolaemariusghergu.queryit.service;

import org.springframework.http.ResponseEntity;
import ro.nicolaemariusghergu.queryit.dto.ProductDto;

import java.util.List;
import java.util.Set;

public interface ProductService {

    ResponseEntity<ProductDto> findProductById(Long id);

    ResponseEntity<List<ProductDto>> getProducts();

    ResponseEntity<Long> addProduct(ProductDto productDto);

    ProductDto getProductByName(String name);

    ResponseEntity<Long> deleteProductById(Long id);

    ResponseEntity<ProductDto> updateProduct(ProductDto productDto);

    ResponseEntity<Set<ProductDto>> getProxyProducts(Long categoryId);

    ResponseEntity<List<ProductDto>> getProductsByCategoryId(Long categoryId);
}
