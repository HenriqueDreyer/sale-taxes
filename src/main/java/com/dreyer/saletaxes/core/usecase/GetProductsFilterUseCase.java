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

    @Inject
    public GetProductsFilterUseCase(GetProductsFilterOutput getProductsFilterOutput, GetProductsFilterGateway getProductsFilterGateway) {
        this.getProductsFilterOutput = getProductsFilterOutput;
        this.getProductsFilterGateway = getProductsFilterGateway;
    }

    @Override
    public void execute(GetProductsFilterRequestModel requestModel) {
        List<ErrorResponseModel> errors = new ArrayList<>();

        if (Objects.isNull(requestModel)) {
            throw new IllegalArgumentException(CommonErrorCode.E0001.getValue());
        }

        if (!isValidIsImported(requestModel.getIsImported())) {
            errors.add(ErrorResponseModel.builder().code(CommonErrorCode.E0002.name()).message(CommonErrorCode.E0002.getValue()).build());
        }

        if (!errors.isEmpty()) {
            this.getProductsFilterOutput.error(errors);
            return;
        }

        final var products = this.getProductsFilterGateway.getProductsByFilter(requestModel.getProductName(), requestModel.getIsImported());

        if (products != null && !products.isEmpty()) {
            this.getProductsFilterOutput.success(products.stream().map(p -> GetProductsFilterResponseModel.builder()
                            .id(p.getId())
                            .name(p.getName())
                            .description("The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested.")
                            .price(p.getPrice())
                            .basicTax(this.getBasicTax(ProductType.getProductTypeFrom(p.getProductType())))
                            .additionalTax(this.getImportTax(p.getIsImported()))
                            .productType(ProductType.getProductTypeFrom(p.getProductType()))
                            .isImported(p.getIsImported())
                            .totalTaxes(this.getTotalTaxes(ProductType.getProductTypeFrom(p.getProductType()), p.getIsImported()))
                            .totalPrice(this.getTotalPrice(p.getPrice(), ProductType.getProductTypeFrom(p.getProductType()), p.getIsImported()))
                            .build())
                    .collect(Collectors.toList()));
        } else {
            this.getProductsFilterOutput.success(Collections.emptyList());
        }
    }

    private Double getTotalPrice(Double price, ProductType productType, String isImported) {
        Double totalTax = this.getTotalTaxes(productType, isImported);
        if(totalTax == 0.0) {
            return price;
        }
        return Math.round((price + totalTax * price) * 20.00) / 20.00;
    }

    private Double getTotalTaxes(ProductType productType, String isImported) {
        Double basicTax = this.getBasicTax(productType);
        Double importTax = this.getImportTax(isImported);

        return Math.round((basicTax + importTax) * 20.0) / 20.0;
    }

    private Double getImportTax(String isImported) {
        final var IMPORT_TAX = 0.05;
        final var EMPTY_TAX = 0.0;

        if (isImported.equalsIgnoreCase("S")) {
            return IMPORT_TAX;
        }
        return EMPTY_TAX;
    }

    private Double getBasicTax(ProductType productType) {
        final var BASIC_TAX = 0.10;
        final var EMPTY_TAX = 0.0;

        if (productType == ProductType.OTHER) {
            return BASIC_TAX;
        }
        return EMPTY_TAX;
    }

    private boolean isValidIsImported(String isImported) {
        if (isImported != null && !isImported.isEmpty() && !isImported.isBlank()) {
            return ProductImportedType.isProductImportedTypeValid(isImported);
        }
        return true;
    }

}
