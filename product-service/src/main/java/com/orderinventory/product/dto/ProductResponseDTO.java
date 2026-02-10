package com.orderinventory.product.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Integer productId;
    private String productName;
    private BigDecimal unitPrice;
    private String colour;
    private String brand;
    private String size;
    private Integer rating;
    private String imageFilename;
}
