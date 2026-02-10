package com.orderinventory.product.controller;

import com.orderinventory.product.dto.ProductRequestDTO;
import com.orderinventory.product.dto.ProductResponseDTO;
import com.orderinventory.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	@Autowired
    private ProductService service;

    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO dto) {
        return service.createProduct(dto);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Integer id) {
        return service.getProductById(id);
    }

    @PostMapping(
            value = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadImage(@PathVariable Integer id,
                              @RequestParam("file") MultipartFile file)
            throws IOException {

        service.uploadProductImage(
                id,
                file.getBytes(),
                file.getOriginalFilename(),
                file.getContentType()
        );

        return "Image uploaded successfully";
    }
}
