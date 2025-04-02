package com.example.springboot_hexagonal.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Product {
    private UUID idProduct;
    private String name;
    private BigDecimal value;
}
