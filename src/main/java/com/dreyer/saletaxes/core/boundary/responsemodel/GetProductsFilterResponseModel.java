package com.dreyer.saletaxes.core.boundary.responsemodel;

import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@ToString
public class GetProductsFilterResponseModel {
    private Long id;
    private String name;
    private Long price;
    private Integer productType;
    private Boolean isImported;
}
