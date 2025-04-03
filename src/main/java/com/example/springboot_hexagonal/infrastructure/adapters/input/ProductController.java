package com.example.springboot_hexagonal.infrastructure.adapters.input;


import com.example.springboot_hexagonal.application.ports.input.ProductInputPort;
import com.example.springboot_hexagonal.application.ports.output.ProductPersistenceOutputPort;
import com.example.springboot_hexagonal.domain.model.Product;
import com.example.springboot_hexagonal.infrastructure.adapters.data.ProductCreateRequest;
import com.example.springboot_hexagonal.infrastructure.adapters.data.ProductResponse;
import com.example.springboot_hexagonal.infrastructure.adapters.input.mappers.ProductRestMapper;
import com.example.springboot_hexagonal.infrastructure.adapters.output.persistence.entity.ProductEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ProductController {

    private final ProductInputPort productInputPort;

    private final ProductRestMapper productRestMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest){
        Product product = productInputPort.saveProduct(productRestMapper.toProduct(productCreateRequest));
        ProductResponse productResponse = productRestMapper.toProductResponse(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> productsList = productInputPort.getAllProducts().stream().map(productRestMapper::toProductResponse).toList();
        productsList.forEach(product -> product.add(linkTo(methodOn(ProductController.class).getOneProduct(product.getIdProduct())).withSelfRel()));
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getOneProduct(@PathVariable("id") UUID id){
        ProductResponse productResponse = productRestMapper.toProductResponse(productInputPort.getOneProduct(id));
        //productResponse.add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") UUID id,
                                                       @RequestBody @Valid ProductCreateRequest productCreateRequest){
        Product product = productInputPort.updateProduct(id,productRestMapper.toProduct(productCreateRequest));
        ProductResponse productResponse = productRestMapper.toProductResponse(product);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") UUID id){
        productInputPort.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }

}