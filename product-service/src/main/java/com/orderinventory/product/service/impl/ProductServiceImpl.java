package com.orderinventory.product.service.impl;

import com.orderinventory.product.dto.ProductRequestDTO;
import com.orderinventory.product.dto.ProductResponseDTO;
import com.orderinventory.product.entity.Product;
import com.orderinventory.product.repository.ProductRepository;
import com.orderinventory.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductRepository repository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = Product.builder()
                .productName(dto.getProductName())
                .unitPrice(dto.getUnitPrice())
                .colour(dto.getColour())
                .brand(dto.getBrand())
                .size(dto.getSize())
                .rating(dto.getRating())
                .build();

        return mapToResponse(repository.save(product));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Integer productId) {
        return repository.findById(productId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void uploadProductImage(Integer productId, byte[] image, String filename, String mimeType) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setProductImage(image);
        product.setImageFilename(filename);
        product.setImageMimeType(mimeType);
        product.setImageLastUpdated(LocalDate.now());

        repository.save(product);
    }

    private ProductResponseDTO mapToResponse(Product product) {
        return ProductResponseDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .unitPrice(product.getUnitPrice())
                .colour(product.getColour())
                .brand(product.getBrand())
                .size(product.getSize())
                .rating(product.getRating())
                .imageFilename(product.getImageFilename())
                .build();
    }
}
