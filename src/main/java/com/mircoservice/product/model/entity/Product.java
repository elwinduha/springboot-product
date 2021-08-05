package com.mircoservice.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "tbl_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Product Name is Required")
    @Column(name = "product_name", length = 150)
    private String name;

    @NotEmpty(message = "Product Description is Required")
    @Column(name = "product_description", length = 500)
    private String description;
    private String price;
}
