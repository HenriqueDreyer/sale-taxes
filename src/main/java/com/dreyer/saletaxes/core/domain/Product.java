package com.dreyer.saletaxes.core.domain;

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
    Long price;
    Integer productType;
    boolean isImported;
}
