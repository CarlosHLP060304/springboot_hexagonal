package com.example.springboot_hexagonal.infrastructure.adapters.input.mappers;

import com.example.springboot_hexagonal.domain.model.Product;
import com.example.springboot_hexagonal.infrastructure.adapters.data.ProductCreateRequest;
import com.example.springboot_hexagonal.infrastructure.adapters.data.ProductResponse;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {

    ProductResponse toProductResponse(Product product);

    Product toProduct(ProductCreateRequest productEntity);
}
