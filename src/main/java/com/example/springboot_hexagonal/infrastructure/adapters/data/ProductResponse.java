package com.example.springboot_hexagonal.infrastructure.adapters.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductResponse extends RepresentationModel<ProductResponse> {
        private UUID idProduct;
        private String name;
        private BigDecimal value;
}

