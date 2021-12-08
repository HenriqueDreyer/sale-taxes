package com.dreyer.saletaxes.core.usecase;

import com.dreyer.saletaxes.core.boundary.input.GetProductsFilterInput;
import com.dreyer.saletaxes.core.boundary.output.GetProductsFilterOutput;
import com.dreyer.saletaxes.core.boundary.requestmodel.GetProductsFilterRequestModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.GetProductsFilterResponseModel;
import com.dreyer.saletaxes.core.domain.entity.ProductType;
import com.dreyer.saletaxes.core.domain.gateway.GetProductsFilterGateway;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class GetProductsFilterUseCase implements GetProductsFilterInput {
    private final GetProductsFilterOutput getProductsFilterOutput;
    private final GetProductsFilterGateway getProductsFilterGateway;

    private final Double BASIC_TAX = 0.1;
    private final Double ADDITIONAL_TAX = 0.05;
    private final Double FREE_BASIC_TAX = 0.0;

    @Inject
    public GetProductsFilterUseCase(GetProductsFilterOutput getProductsFilterOutput,
                                    GetProductsFilterGateway getProductsFilterGateway) {
        this.getProductsFilterOutput = getProductsFilterOutput;
        this.getProductsFilterGateway = getProductsFilterGateway;
    }

    @Override
    public void execute(GetProductsFilterRequestModel getProductsFilterRequestModel) {
        List<ErrorResponseModel> errors = new ArrayList<>();

        if (Objects.isNull(getProductsFilterRequestModel)) {
            errors.add(ErrorResponseModel.builder()
                    .code(CommonErrorCode.E0001.name())
                    .message(CommonErrorCode.E0001.getValue())
                    .build());
        }

        if (!errors.isEmpty()) {
            this.getProductsFilterOutput.error(errors);
            return;
        }

        final var products = this.getProductsFilterGateway.getProductsByFilter(
                getProductsFilterRequestModel.getProductName(),
                getProductsFilterRequestModel.getIsImported()
        );

        if (products != null && !products.isEmpty()) {
            this.getProductsFilterOutput.success(products.stream().map(p -> GetProductsFilterResponseModel.builder()
                            .id(p.getId())
                            .name(p.getName())
                            .price(p.getPrice())
                            .basicTax(this.calcBasicTax(p.getPrice(), ProductType.getProductTypeFrom(p.getProductType())))
                            .additionalTax(this.calcAdditionalTax(p.getPrice()))
                            .productType(ProductType.getProductTypeFrom(p.getProductType()))
                            .isImported(p.getIsImported())
                            .build())
                    .collect(Collectors.toList()));
        } else {
            this.getProductsFilterOutput.success(Collections.emptyList());
        }
    }

    private Double calcAdditionalTax(Double price) {
        return Math.round((price * ADDITIONAL_TAX) * 20.0) / 20.0;
    }

    private Double calcBasicTax(Double price, ProductType productType) {
        switch (productType) {
            case FOOD:
            case MEDKIT:
            case BOOK:
                return FREE_BASIC_TAX;
            default:
                return Math.round((price * BASIC_TAX) * 20.0) / 20.0;
        }
    }

}
