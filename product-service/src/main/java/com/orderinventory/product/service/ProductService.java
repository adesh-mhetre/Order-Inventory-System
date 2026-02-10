package com.orderinventory.product.service;

import com.orderinventory.product.dto.ProductRequestDTO;
import com.orderinventory.product.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO dto);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Integer productId);

    void uploadProductImage(Integer productId, byte[] image, String filename, String mimeType);
}
