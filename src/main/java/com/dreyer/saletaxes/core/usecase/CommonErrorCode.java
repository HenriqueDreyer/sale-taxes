package com.dreyer.saletaxes.core.usecase;

public enum CommonErrorCode {
    E0001("The request model cannot be null"),
    E0002("The imported parameter is invalid"),
    E0003("The name parameter is invalid");

    private final String value;

    public String getValue() { return this.value; }

    private CommonErrorCode(String value) { this.value = value; }
}
