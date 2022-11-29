package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.model.Product

@Mapper
interface ProductMapper {
    fun productToProductDto(product: Product): ProductDto
    fun productDtoToProduct(productDto: ProductDto): Product
    fun mapEmptyString(string: String): String? {
        return string.ifEmpty { null }
    }

    companion object {
        val INSTANCE: ProductMapper = Mappers.getMapper(ProductMapper::class.java)
    }
}