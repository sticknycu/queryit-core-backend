package ro.nicolaemariusghergu.queryit.mapper

import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import ro.nicolaemariusghergu.queryit.dto.CategoryDto
import ro.nicolaemariusghergu.queryit.model.Category

@Mapper
interface CategoryMapper {
    fun categoryToCategoryDto(category: Category): CategoryDto
    fun categoryDtoToCategory(categoryDto: CategoryDto): Category

    companion object {
        val INSTANCE: CategoryMapper = Mappers.getMapper(CategoryMapper::class.java)
    }
}