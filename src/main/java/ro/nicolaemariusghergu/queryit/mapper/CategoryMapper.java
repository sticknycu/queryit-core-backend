package ro.nicolaemariusghergu.queryit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.nicolaemariusghergu.queryit.dto.CategoryDto;
import ro.nicolaemariusghergu.queryit.model.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    CategoryDto categoryToCategoryDto(Category category);
}
