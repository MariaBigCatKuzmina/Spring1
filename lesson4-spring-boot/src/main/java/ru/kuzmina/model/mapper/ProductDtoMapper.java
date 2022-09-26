package ru.kuzmina.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.kuzmina.model.Dto.ProductDto;
import ru.kuzmina.model.Product;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {

    ProductDto map(Product product);
  //  @Mapping(target = "id", ignore = true)
    Product map(ProductDto productDto);

}
