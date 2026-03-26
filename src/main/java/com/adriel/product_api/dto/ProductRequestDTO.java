package com.adriel.product_api.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private String productIdentifier;
}
