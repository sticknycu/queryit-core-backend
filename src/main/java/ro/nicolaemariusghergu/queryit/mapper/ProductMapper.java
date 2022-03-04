package ro.nicolaemariusghergu.queryit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.nicolaemariusghergu.queryit.dto.ProductDto;
import ro.nicolaemariusghergu.queryit.model.Product;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "iconUrl", target = "iconUrl")
    ProductDto productToProductDto(Product product);
}
