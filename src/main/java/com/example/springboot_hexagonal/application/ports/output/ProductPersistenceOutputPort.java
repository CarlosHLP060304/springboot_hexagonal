package com.example.springboot_hexagonal.application.ports.output;

import com.example.springboot_hexagonal.domain.model.Product;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public interface ProductPersistenceOutputPort {

        Product saveProduct(Product product);

        List<Product> getAllProducts();

        Product getOneProduct(UUID id) ;

        Product updateProduct(UUID id, Product product);

        public void deleteProduct(UUID id);

}
