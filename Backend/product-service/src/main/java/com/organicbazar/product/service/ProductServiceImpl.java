package com.organicbazar.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.organicbazar.product.client.CategoryClient;
import com.organicbazar.product.dto.CategoryDto;
import com.organicbazar.product.dto.ProductDto;
import com.organicbazar.product.dto.ProductResponseDto;
import com.organicbazar.product.dto.ProductStockRequest;
import com.organicbazar.product.entity.Product;
import com.organicbazar.product.exception.ProductNotFoundException;
import com.organicbazar.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryClient categoryClient;

    @Override
    public ProductResponseDto createProduct(ProductDto dto) {
        CategoryDto category = categoryClient.getCategoryById(dto.getCategoryId());
        if (category == null) {
            throw new ProductNotFoundException("Category not found");
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .categoryId(category.getId())
                .categoryName(category.getName())
                .createdBy(username)
                .build();

        return mapToDto(productRepository.save(product));
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return mapToDto(product);
    }

    @Override
    public void deleteProduct(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (!product.getCreatedBy().equals(username)) {
            throw new RuntimeException("Unauthorized to delete this product");
        }

        productRepository.delete(product);
    }

    @Override
    public List<ProductResponseDto> getMyProducts() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return productRepository.findAllByCreatedBy(username)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        if (!product.getCreatedBy().equals(username)) {
            throw new RuntimeException("Unauthorized to update this product");
        }

        CategoryDto category = categoryClient.getCategoryById(dto.getCategoryId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategoryId(category.getId());
        product.setCategoryName(category.getName());

        return mapToDto(productRepository.save(product));
    }

    private ProductResponseDto mapToDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategoryId(product.getCategoryId());
        dto.setCategoryName(product.getCategoryName());
        dto.setCreatedBy(product.getCreatedBy());
        return dto;
    }
    
    @Override
    public void deductStock(List<ProductStockRequest> requests) {
        for (ProductStockRequest req : requests) {
            Product product = productRepository.findById(req.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < req.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - req.getQuantity());
            productRepository.save(product);
        }
    }
    
    @Override
    public List<ProductResponseDto> getProductsByCreatedBy(String email) {
        return productRepository.findByCreatedBy(email).stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

}
