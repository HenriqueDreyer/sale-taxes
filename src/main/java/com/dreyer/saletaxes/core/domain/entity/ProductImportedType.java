package com.dreyer.saletaxes.core.domain.entity;

public enum ProductImportedType {
    YES("S"),
    NO("N");

    private final String value;

    public String getValue() { return this.value; }

    public static ProductImportedType getProductImportedTypeFrom(String value) {
        for(ProductImportedType type: values()) {
            if(type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

    public static boolean isProductImportedTypeValid(String value) {
        for(ProductImportedType type: values()) {
            if(type.value.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    private ProductImportedType(String value) { this.value = value; }
}
