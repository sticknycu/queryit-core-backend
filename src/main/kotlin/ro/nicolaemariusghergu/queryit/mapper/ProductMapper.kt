package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.ProductDto
import ro.nicolaemariusghergu.queryit.model.Product

@Mapper
interface ProductMapper {
    open fun productToProductDto(product: Product?): ProductDto?
    open fun productDtoToProduct(productDto: ProductDto?): Product?
    fun mapEmptyString(string: String?): String? {
        return if (string != null && !string.isEmpty()) string else null
    }

    companion object {
        val INSTANCE = Mappers.getMapper(ProductMapper::class.java)
    }
}