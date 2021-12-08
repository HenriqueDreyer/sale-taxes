package com.dreyer.saletaxes.core.domain.entity;

public enum ProductType {
    BOOK(1),
    FOOD(2),
    MEDKIT(3);

    private final Integer value;

    public Integer getValue() { return this.value; }

    public static ProductType getProductTypeFrom(Integer value) {
        for(ProductType type: values()) {
            if(type.value == value) {
                return type;
            }
        }
        return null;
    }

    private ProductType(Integer value) { this.value = value; }
}
