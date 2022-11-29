package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.model.Category

@Mapper
interface CategoryMapper {
    fun categoryToCategoryDto(category: Category): CategoryDto
    fun categoryDtoToCategory(categoryDto: CategoryDto): Category
    fun mapEmptyString(string: String): String? {
        return string.ifEmpty { null }
    }

    companion object {
        val INSTANCE = Mappers.getMapper(CategoryMapper::class.java)
    }
}