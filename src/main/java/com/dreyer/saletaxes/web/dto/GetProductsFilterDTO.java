package com.dreyer.saletaxes.web.dto;

import com.dreyer.saletaxes.core.boundary.responsemodel.GetProductsFilterResponseModel;
import com.dreyer.saletaxes.core.domain.entity.ProductType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode
@ToString
public class GetProductsFilterDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer productType;
    private String isImported;

    public static GetProductsFilterDTO buildFromModel(GetProductsFilterResponseModel responseModel) {
        return new GetProductsFilterDTO(
                responseModel.getId(),
                responseModel.getName(),
                responseModel.getDescription(),
                responseModel.getPrice(),
                responseModel.getProductType().getValue(),
                responseModel.getIsImported()
        );
    }
}
