package com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.mappers;

import com.example.springboot_hexagonal.domain.model.Product;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;
import org.springframework.web.bind.annotation.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);
}
