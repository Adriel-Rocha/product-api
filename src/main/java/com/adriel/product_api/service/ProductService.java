package com.adriel.product_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adriel.product_api.dto.ProductRequestDTO;
import com.adriel.product_api.dto.ProductResponseDTO;
import com.adriel.product_api.model.Product;
import com.adriel.product_api.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO create(ProductRequestDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
        
        Product saved = productRepository.save(product);

        return mapToResponse(saved);
    }

    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        return mapToResponse(product);
    }

    private ProductResponseDTO mapToResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .productIdentifier(product.getProductIdentifier())
                .build();
    }
}
