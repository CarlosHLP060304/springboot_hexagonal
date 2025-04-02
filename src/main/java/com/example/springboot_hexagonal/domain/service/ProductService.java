package com.example.springboot_hexagonal.domain.service;

import com.example.springboot_hexagonal.application.ports.input.ProductInputPort;
import com.example.springboot_hexagonal.application.ports.output.ProductPersistenceOutputPort;
import com.example.springboot_hexagonal.domain.model.Product;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ProductService implements ProductInputPort {
   
        private final ProductPersistenceOutputPort productPersistenceOutputPort;

        @Override
        public Product saveProduct(Product product) {
            return productPersistenceOutputPort.saveProduct(product);
        }

        @Override
        public List<Product> getAllProducts() {
            return productPersistenceOutputPort.getAllProducts();
        }

        @Override
        public Product getOneProduct(UUID id) {
            return productPersistenceOutputPort.getOneProduct(id);
        }

        @Override
        public Product updateProduct(UUID id, Product product) {
            return productPersistenceOutputPort.updateProduct(id,product);
        }

        @Override
        public void deleteProduct(UUID id) {
            productPersistenceOutputPort.deleteProduct(id);
        }
}
