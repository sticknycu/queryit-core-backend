package ro.nicolaemariusghergu.queryit.service.impl

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.gateway.ProductProxy
import ro.nicolaemariusghergu.queryit.mapper.ProductMapper
import ro.nicolaemariusghergu.queryit.model.Product
import ro.nicolaemariusghergu.queryit.repository.ProductRepository
import ro.nicolaemariusghergu.queryit.service.ProductService

@Service
class ProductServiceImpl(private val productRepository: ProductRepository,
private val productProxy: ProductProxy) : ProductService {
    override fun findProductById(id: Long): ResponseEntity<ProductDto> {
        return ResponseEntity.ok(productRepository.findById(id).stream()
                .map { product: Product -> ProductMapper.INSTANCE.productToProductDto(product) }
                .findFirst()
                .orElseThrow { NoSuchElementException("Product does not exist!") }
        )
    }

    override fun getProducts(): ResponseEntity<List<ProductDto>> {
        return ResponseEntity.ok(productRepository.findAll()
                .stream()
                .map {product: Product -> ProductMapper.INSTANCE.productToProductDto(product) }
                .toList())
    }

    override fun addProduct(productDto: ProductDto): ResponseEntity<Long> {
        productRepository.save(ProductMapper.INSTANCE.productDtoToProduct(productDto))
        return ResponseEntity.ok(productDto.id)
    }

    override fun getProductByName(name: String): ProductDto {
        return ProductMapper.INSTANCE.productToProductDto(
                productRepository.findByName(name)
                        .orElseThrow { NoSuchElementException("Product cannot be found") }
        )
    }

    override fun deleteProductById(id: Long): ResponseEntity<Long> {
        productRepository.deleteById(id)
        return ResponseEntity.ok(id)
    }

    override fun updateProduct(productDto: ProductDto): ResponseEntity<ProductDto> {
        val modifiedProduct: Product = productRepository.findById(productDto.id).stream()
                .map { product: Product ->
                    product.quantity = productDto.quantity
                    product
                }
                .findAny()
                .orElseThrow { NoSuchElementException("Product has not been found") }
        productRepository.save(modifiedProduct)
        return ResponseEntity.ok(ProductMapper.INSTANCE.productToProductDto(modifiedProduct))
    }

    override fun getProxyProducts(categoryId: Long): ResponseEntity<Set<ProductDto>> {
        return productProxy.getProductsFromMegaImageByCategory(categoryId)
    }

    override fun getProductsByCategoryId(categoryId: Long): ResponseEntity<List<ProductDto>> {
        return ResponseEntity.ok(productRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(ProductMapper.INSTANCE::productToProductDto)
                .toList())
    }
}