package com.adriel.product_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adriel.product_api.dto.CategoryDTO;
import com.adriel.product_api.dto.ProductRequestDTO;
import com.adriel.product_api.dto.ProductResponseDTO;
import com.adriel.product_api.model.Category;
import com.adriel.product_api.model.Product;
import com.adriel.product_api.repository.CategoryRepository;
import com.adriel.product_api.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public List<ProductResponseDTO> getAll() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ProductResponseDTO> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        
        return products.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProductResponseDTO findById(Long productId) {
        Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        return mapToResponse(product);
    }

    public ProductResponseDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);

        if(product != null) {
            return mapToResponse(product);
        }

        return null;
    }

    public ProductResponseDTO create(ProductRequestDTO productDTO) {

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        
        Product product = Product.builder()
                .productIdentifier(productDTO.getProductIdentifier())
                .productName(productDTO.getProductName())
                .productDescription(productDTO.getProductDescription())
                .price(productDTO.getPrice())
                .category(category)
                .build();
        
        Product saved = productRepository.save(product);

        return mapToResponse(saved);
    }

    public ProductResponseDTO delete(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if(product.isPresent()) {
            productRepository.delete(product.get());
            
            return mapToResponse(product.get());
        }

        return null;

    }

    private ProductResponseDTO mapToResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getProductName())
                .description(product.getProductDescription())
                .price(product.getPrice())
                .productIdentifier(product.getProductIdentifier())
                .category(product.getCategory() != null ? mapToCategoryDTO(product.getCategory()) : null)
                .build();
    }

    private CategoryDTO mapToCategoryDTO(Category category) {

        if(category == null) {
            return null;
        }

        return CategoryDTO.builder()
        .id(category.getId())
        .name(category.getCategoryName())
        .build();
    }
}
