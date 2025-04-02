package com.example.springboot_hexagonal.infrastructure.adapters.input.mappers;

import com.example.springboot_hexagonal.domain.model.Product;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProductRestMapper {

    ProductEntity toProductEntity(Product product);

    Product toProduct(ProductEntity productEntity);
}
