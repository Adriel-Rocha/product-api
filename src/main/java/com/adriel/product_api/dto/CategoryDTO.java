package com.adriel.product_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {

    @NotNull
    private Long id;
    private String name;
}
