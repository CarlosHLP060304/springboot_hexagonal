package com.example.springboot_hexagonal.infrastructure.adapters.input;


import com.example.springboot_hexagonal.application.ports.output.ProductPersistenceOutputPort;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductPersistenceOutputPort productPersistenceOutputPort;

    @PostMapping
    public ResponseEntity<ProductEntity> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
        List<ProductEntity> productsList = productService.getAllProducts();
        productsList.forEach(product -> product.add(linkTo(methodOn(ProductController.class).getOneProduct(product.getIdProduct())).withSelfRel()));
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getOneProduct(@PathVariable("id") UUID id){
        ProductEntity productModel = productService.getOneProduct(id);
        productModel.add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(productModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable("id") UUID id,
                                                       @RequestBody @Valid ProductRecordDto productRecordDto){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id,productRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }

}