package com.example.springboot_hexagonal.application.ports.input;

import com.example.springboot_hexagonal.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductInputPort {

        Product saveProduct(Product product);

        List<Product> getAllProducts();

        Product getOneProduct(UUID id);

        Product updateProduct(UUID id, Product product);

        public void deleteProduct(UUID id);

}
