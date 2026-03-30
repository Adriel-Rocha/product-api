package com.adriel.product_api.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    
    private Long id;
    private String productIdentifier;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryDTO category;
}
