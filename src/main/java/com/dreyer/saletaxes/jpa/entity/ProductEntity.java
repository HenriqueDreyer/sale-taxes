package com.dreyer.saletaxes.jpa.entity;

import com.dreyer.saletaxes.core.domain.entity.Product;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@SqlResultSetMapping(name = "ProductsFilterMapping",
        classes = @ConstructorResult(
                targetClass = Product.class,
                columns = {@ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "price", type = Double.class),
                        @ColumnResult(name = "product_type", type = Integer.class),
                        @ColumnResult(name = "is_imported", type = String.class)
                }
        )
)
@Entity
@DynamicUpdate
@Table(name = "Product")
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @Column(name = "id", length = 10, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "product_type", nullable = false)
    private Integer productType;

    @Column(name = "is_imported", nullable = false)
    private String isImported;
}
