package com.dreyer.saletaxes.core.boundary.requestmodel;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@ToString
@SuperBuilder
@EqualsAndHashCode
public class GetProductsFilterRequestModel {
    private String productName;
    private boolean isImported;
}
