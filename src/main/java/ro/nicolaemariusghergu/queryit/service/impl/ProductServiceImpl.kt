package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.mapper.ProductMapper
import ro.nicolaemariusghergu.queryit.model.Product
import ro.nicolaemariusghergu.queryit.service.ProductService
import java.util.function.Function
import java.util.function.Supplier

@Service
class ProductServiceImpl : ProductService {
    override fun findProductById(id: Long?): ResponseEntity<ProductDto?>? {
        return ResponseEntity.ok(productRepository.findById(id).stream()
                .map<ProductDto?>(Function<Product?, ProductDto?> { product: Product? -> ProductMapper.Companion.INSTANCE.productToProductDto(product) })
                .findFirst()
                .orElseThrow<NoSuchElementException?>(
                        Supplier { NoSuchElementException("Product does not exist!") })
        )
    }

    override fun getProducts(): ResponseEntity<MutableList<ProductDto?>?>? {
        return ResponseEntity.ok(productRepository.findAll()
                .stream()
                .map<ProductDto?>(Function<Product?, ProductDto?> { product: Product? -> ProductMapper.Companion.INSTANCE.productToProductDto(product) })
                .toList())
    }

    override fun addProduct(productDto: ProductDto?): ResponseEntity<Long?>? {
        productRepository.save<Product?>(ProductMapper.Companion.INSTANCE.productDtoToProduct(productDto))
        return ResponseEntity.ok(productDto.getId())
    }

    override fun getProductByName(name: String?): ProductDto? {
        return ProductMapper.Companion.INSTANCE.productToProductDto(
                productRepository.findByName(name)
                        .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Product cannot be found") })
        )
    }

    override fun deleteProductById(id: Long?): ResponseEntity<Long?>? {
        productRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateProduct(productDto: ProductDto?): ResponseEntity<ProductDto?>? {
        val modifiedProduct: ProductDto = productRepository.findById(productDto.getId()).stream()
                .map<ProductDto?>(Function<Product?, ProductDto?> { product: Product? -> ProductMapper.Companion.INSTANCE.productToProductDto(product) })
                .map<ProductDto?>(Function { updatedProduct: ProductDto? ->
                    updatedProduct.setQuantity(productDto.getQuantity())
                    updatedProduct
                })
                .findAny()
                .orElseThrow<NoSuchElementException?>(Supplier { NoSuchElementException("Product has not been found") }
                )
        productRepository.save<Product?>(
                ProductMapper.Companion.INSTANCE.productDtoToProduct(modifiedProduct))
        return ResponseEntity.ok(modifiedProduct)
    }

    override fun getProxyProducts(categoryId: Long?): ResponseEntity<MutableSet<ProductDto?>?>? {
        return productProxy.getProductsFromMegaImageByCategory(categoryId)
    }

    override fun getProductsByCategoryId(categoryId: Long?): ResponseEntity<MutableList<ProductDto?>?>? {
        return ResponseEntity.ok(productRepository.findAllByCategoryId(categoryId)
                .stream()
                .map<ProductDto?>(Function<Product?, ProductDto?> { product: Product? -> ProductMapper.Companion.INSTANCE.productToProductDto(product) })
                .toList())
    }
}