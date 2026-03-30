package com.adriel.product_api.dto;

import java.math.BigDecimal;


import lombok.Data;

@Data
public class ProductRequestDTO {
    
    private String productIdentifier;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private Long categoryId;
}
