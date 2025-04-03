package com.example.springboot_hexagonal.infrastructure.adapters;

import com.example.springboot_hexagonal.application.ports.output.ProductPersistenceOutputPort;
import com.example.springboot_hexagonal.domain.model.Product;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.mappers.ProductPersistenceMapper;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistenceOutputPort {

    private final ProductRepository productRepository;
    private final ProductPersistenceMapper productPersistenceMapper;

    @Override
    public Product saveProduct(Product product) {
        var productEntity = productRepository.save(productPersistenceMapper.toProductEntity(product));
        return productPersistenceMapper.toProduct(productEntity);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(productPersistenceMapper::toProduct).toList();
    }

    @Override
    public Product getOneProduct(UUID id) {
        ProductEntity productEntity =  productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND,"Product not found")
        );
        return productPersistenceMapper.toProduct(productEntity);
    }

    @Override
    public Product updateProduct(UUID id, Product product) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND,"Product not found")
        );

        return productPersistenceMapper.toProduct(productRepository.save(productEntity));
    }

    @Override
    public void deleteProduct(UUID id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND,"Product not found")
        );
        productRepository.delete(productEntity);
    }
}
