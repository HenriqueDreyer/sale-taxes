package com.dreyer.saletaxes.jpa.query;

import com.dreyer.saletaxes.core.domain.entity.Product;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;

@MappedSuperclass
@SqlResultSetMapping(name = PojoMapping.PRODUCTS_FILTER_MAPPING,
        classes = @ConstructorResult(
                targetClass = Product.class,
                columns = {@ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "price", type = Double.class),
                        @ColumnResult(name = "productType", type = Integer.class),
                        @ColumnResult(name = "isImported", type = String.class)
                }
        )
)
public abstract class PojoMapping {
    public static final String PRODUCTS_FILTER_MAPPING = "ProductsFilterMapping";
}
