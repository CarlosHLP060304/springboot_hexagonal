package com.example.springboot_hexagonal.infrastructure.adapters.configs;

import com.example.springboot_hexagonal.application.ports.output.ProductPersistenceOutputPort;
import com.example.springboot_hexagonal.domain.service.ProductService;
import com.example.springboot_hexagonal.infrastructure.adapters.ProductPersistenceAdapter;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapper;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ProductService productService(ProductPersistenceOutputPort productPersistenceOutputPort){
        return new ProductService(productPersistenceOutputPort);
    }

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter(ProductRepository productRepository, ProductPersistenceMapper productPersistenceMapper){
        return new ProductPersistenceAdapter(productRepository,productPersistenceMapper);
    }


}
