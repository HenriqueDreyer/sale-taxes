package com.dreyer.saletaxes.core.domain.entity;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class Product {
    Long id;
    String name;
    Double price;
    Integer productType;
    String isImported;
}
