package com.adriel.product_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriel.product_api.dto.ProductRequestDTO;
import com.adriel.product_api.dto.ProductResponseDTO;
import com.adriel.product_api.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
    }

    @GetMapping("/code/{productIdentifier}")
    public ResponseEntity<ProductResponseDTO> findByProductIdentifier(@PathVariable String productIdentifier) {
        return ResponseEntity.ok(productService.findByProductIdentifier(productIdentifier));
    }


    @PostMapping
    public ResponseEntity<ProductResponseDTO> newProduct(@RequestBody ProductRequestDTO productDTO) {
        return ResponseEntity.ok(productService.create(productDTO));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> delete(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.delete(productId));
    }
}
