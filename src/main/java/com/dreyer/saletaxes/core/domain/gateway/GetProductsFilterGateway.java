package com.dreyer.saletaxes.core.domain.gateway;

import com.dreyer.saletaxes.core.domain.entity.Product;

import java.util.List;

public interface GetProductsFilterGateway {
    List<Product> getProductsByFilter(String productName, String isImported);
}
