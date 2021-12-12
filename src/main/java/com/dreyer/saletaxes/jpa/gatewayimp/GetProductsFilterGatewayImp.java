package com.dreyer.saletaxes.jpa.gatewayimp;

import com.dreyer.saletaxes.core.domain.entity.Product;
import com.dreyer.saletaxes.core.domain.gateway.GetProductsFilterGateway;
import com.dreyer.saletaxes.jpa.query.NativeSQLQuery;
import com.dreyer.saletaxes.jpa.query.ProductQueryTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Repository
public class GetProductsFilterGatewayImp implements GetProductsFilterGateway {
    private final EntityManager entityManager;

    private final String PRODUCT_NAME_PARAM = "productName";
    private final String IS_IMPORTED_PARAM = "productImported";

    @Autowired
    public GetProductsFilterGatewayImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> getProductsByFilter(String productName, String isImported) {
        final var params = new StringBuilder();

        if(isProductNameValid(productName)) {
            params.append(ProductQueryTemplate.PRODUCT_NAME_FILTER);
        }

        if(isImportedValid(isImported)) {
            params.append(ProductQueryTemplate.IS_IMPORTED_FILTER);
        }

        final var nativeQuery = String.format(NativeSQLQuery.PRODUCTS_FILTER_NATIVE_QUERY, params);

        final var query = this.entityManager.createNativeQuery(nativeQuery, "ProductsFilterMapping");

        if(isProductNameValid(productName)) {
            String nameParam = String.format("%%%s%%", productName.toUpperCase());
            query.setParameter(PRODUCT_NAME_PARAM, nameParam);
        }

        if(isImportedValid(isImported)) {
            query.setParameter(IS_IMPORTED_PARAM, isImported);
        }

        return (List<Product>) query.getResultList();
    }

    private boolean isProductNameValid(String value) {
        if(Objects.isNull(value) || value.isBlank() || value.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean isImportedValid(String value) {
        if(Objects.isNull(value) || value.isBlank() || value.isEmpty()) {
            return false;
        }
        return true;
    }
}
