package com.dreyer.saletaxes.jpa.query;

public class ProductQueryTemplate {

    public static final String PRODUCT_NAME_FILTER;
    public static final String IS_IMPORTED_FILTER;

    static {
        PRODUCT_NAME_FILTER = " AND NAME LIKE (%:productName%)";
        IS_IMPORTED_FILTER = " AND IS_IMPORTED = :productImported";
    }
}
