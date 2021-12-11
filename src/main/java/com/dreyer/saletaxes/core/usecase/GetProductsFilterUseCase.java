package com.dreyer.saletaxes.core.usecase;

import com.dreyer.saletaxes.core.boundary.input.GetProductsFilterInput;
import com.dreyer.saletaxes.core.boundary.output.GetProductsFilterOutput;
import com.dreyer.saletaxes.core.boundary.requestmodel.GetProductsFilterRequestModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.GetProductsFilterResponseModel;
import com.dreyer.saletaxes.core.domain.entity.ProductImportedType;
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
    public void execute(GetProductsFilterRequestModel requestModel) {
        List<ErrorResponseModel> errors = new ArrayList<>();

        if (Objects.isNull(requestModel)) {
            throw new IllegalArgumentException(CommonErrorCode.E0001.getValue());
        }

        if(!isValidIsImported(requestModel.getIsImported())) {
            errors.add(ErrorResponseModel.builder()
                    .code(CommonErrorCode.E0002.name())
                    .message(CommonErrorCode.E0002.getValue())
                    .build());
        }

        if (!errors.isEmpty()) {
            this.getProductsFilterOutput.error(errors);
            return;
        }

        final var products = this.getProductsFilterGateway.getProductsByFilter(
                requestModel.getProductName(),
                requestModel.getIsImported()
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

    private boolean isValidIsImported(String isImported) {
        if(isImported != null && !isImported.isEmpty() && !isImported.isBlank()) {
            return ProductImportedType.isProductImportedTypeValid(isImported);
        }
        return true;
    }

    private Double calcAdditionalTax(Double price) {
        return ADDITIONAL_TAX;
    }

    private Double calcBasicTax(Double price, ProductType productType) {
        switch (productType) {
            case FOOD:
            case MEDKIT:
            case BOOK:
                return FREE_BASIC_TAX;
            default:
                return BASIC_TAX;
        }
    }

}
