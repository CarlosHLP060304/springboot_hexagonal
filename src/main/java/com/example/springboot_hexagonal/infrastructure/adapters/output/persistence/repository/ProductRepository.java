package com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.repository;

import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}