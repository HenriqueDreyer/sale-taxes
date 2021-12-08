package com.dreyer.saletaxes.jpa.query;

public class ProductQueryTemplate {

    public static final String PRODUCT_NAME_FILTER;
    public static final String IS_IMPORTED_FILTER;

    static {
        PRODUCT_NAME_FILTER = " AND PRODUCT_NAME LIKE ('%:productName%')";
        IS_IMPORTED_FILTER = " AND PRODUCT_IMPORTED LIKE ('%:productImported%')";
    }
}
