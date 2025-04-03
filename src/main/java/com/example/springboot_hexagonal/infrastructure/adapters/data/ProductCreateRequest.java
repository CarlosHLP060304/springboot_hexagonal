package com.example.springboot_hexagonal.infrastructure.adapters.data;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductCreateRequest(
        String name,
        BigDecimal value
) {
}
