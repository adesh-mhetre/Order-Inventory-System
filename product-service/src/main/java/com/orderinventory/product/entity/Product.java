package com.orderinventory.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    @Column(name = "unit_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "colour", length = 45)
    private String colour;

    @Column(name = "brand", length = 45)
    private String brand;

    @Column(name = "size", length = 10)
    private String size;

    @Column(name = "rating")
    private Integer rating;

    // -------- Image Fields (MySQL) --------

    @Lob
    @Column(name = "product_image", columnDefinition = "LONGBLOB")
    private byte[] productImage;

    @Column(name = "image_mime_type", length = 100)
    private String imageMimeType;

    @Column(name = "image_filename", length = 255)
    private String imageFilename;

    @Column(name = "image_last_updated")
    private LocalDate imageLastUpdated;
}
