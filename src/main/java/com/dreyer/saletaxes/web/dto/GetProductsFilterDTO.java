package com.dreyer.saletaxes.web.dto;

import com.dreyer.saletaxes.core.boundary.responsemodel.GetProductsFilterResponseModel;
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
    private Double price;
    private Double basicTax;
    private Double additionalTax;
    private Integer productType;
    private String isImported;

    public static GetProductsFilterDTO buildFromModel(GetProductsFilterResponseModel responseModel) {
        return new GetProductsFilterDTO(
                responseModel.getId(),
                responseModel.getName(),
                responseModel.getPrice(),
                responseModel.getBasicTax(),
                responseModel.getAdditionalTax(),
                responseModel.getProductType().getValue(),
                responseModel.getIsImported()
        );
    }
}
