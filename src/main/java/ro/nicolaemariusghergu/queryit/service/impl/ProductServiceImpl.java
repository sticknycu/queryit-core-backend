package ro.nicolaemariusghergu.queryit.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.nicolaemariusghergu.queryit.dto.ProductDto;
import ro.nicolaemariusghergu.queryit.mapper.ProductMapper;
import ro.nicolaemariusghergu.queryit.proxy.ProductProxy;
import ro.nicolaemariusghergu.queryit.repository.ProductRepository;
import ro.nicolaemariusghergu.queryit.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public record ProductServiceImpl(ProductRepository productRepository,
                                 ProductProxy productProxy)
        implements ProductService {

    @Override
    public ResponseEntity<ProductDto> findProductById(Long id) {
        return ResponseEntity.ok(productRepository.findById(id).stream()
                        .map(ProductMapper.INSTANCE::productToProductDto)
                        .findFirst()
                        .orElseThrow(
                                () -> new NoSuchElementException("Product does not exist!"))
                );
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList());
    }

    @Override
    public ResponseEntity<Long> addProduct(ProductDto productDto) {
        productRepository.save(ProductMapper.INSTANCE.productDtoToProduct(productDto));
        return ResponseEntity.ok(productDto.getId());
    }

    @Override
    public ProductDto getProductByName(String name) {
        return ProductMapper.INSTANCE.productToProductDto(
                productRepository.findByName(name)
                        .orElseThrow(() ->
                                new NoSuchElementException("Product cannot be found"))
        );
    }

    @Override
    public ResponseEntity<Long> deleteProductById(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(ProductDto productDto) {
        ProductDto modifiedProduct = productRepository.findById(productDto.getId()).stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .map(updatedProduct -> {
                    updatedProduct.setQuantity(productDto.getQuantity());
                    return updatedProduct;
                })
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Product has not been found")
                );
        productRepository.save(
                ProductMapper.INSTANCE.productDtoToProduct(modifiedProduct));
        return ResponseEntity.ok(modifiedProduct);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProxyProducts(Long categoryId) {
        return ResponseEntity.ok(productProxy.getProducts(categoryId).stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList());
    }

    @Override
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(Long categoryId) {
        return ResponseEntity.ok(productRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList());
    }

}
