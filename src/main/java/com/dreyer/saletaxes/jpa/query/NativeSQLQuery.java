package com.dreyer.saletaxes.jpa.query;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class NativeSQLQuery {
    public static final String PRODUCTS_FILTER_NATIVE_QUERY;

    static {
        PRODUCTS_FILTER_NATIVE_QUERY = getResourceFileAsString("getProductsFilter.sql");
    }

    private NativeSQLQuery() {}

    public static String getResourceFileAsString(final String filename) {
        final var inputStream = getResourceFileAsInputStream(filename);
        if(inputStream != null) {
            final var reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }else{
            throw new IllegalArgumentException(String.format("Resource not found: %s", filename));
        }
    }

    private static InputStream getResourceFileAsInputStream(final String filename) {
        final var classLoader = NativeSQLQuery.class.getClassLoader();
        return classLoader.getResourceAsStream(filename);
    }
}
