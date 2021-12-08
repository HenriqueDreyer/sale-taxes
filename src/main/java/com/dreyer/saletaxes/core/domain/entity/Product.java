package com.dreyer.saletaxes.core.domain.entity;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@With
@EqualsAndHashCode
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Integer productType;
    private String isImported;
}
