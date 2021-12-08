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
    private Long price;
    private Integer productType;
    private Boolean isImported;

    public static GetProductsFilterDTO buildFromModel(GetProductsFilterResponseModel responseModel) {
        return new GetProductsFilterDTO(
                responseModel.getId(),
                responseModel.getName(),
                responseModel.getPrice(),
                responseModel.getProductType(),
                responseModel.getIsImported()
        );
    }
}
