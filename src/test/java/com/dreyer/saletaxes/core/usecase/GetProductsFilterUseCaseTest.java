package com.dreyer.saletaxes.core.usecase;

import com.dreyer.saletaxes.core.boundary.output.GetProductsFilterOutput;
import com.dreyer.saletaxes.core.boundary.requestmodel.GetProductsFilterRequestModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.GetProductsFilterResponseModel;
import com.dreyer.saletaxes.core.domain.entity.Product;
import com.dreyer.saletaxes.core.domain.entity.ProductType;
import com.dreyer.saletaxes.core.domain.gateway.GetProductsFilterGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetProductsFilterUseCaseTest {

    @Mock
    private GetProductsFilterOutput getProductsFilterOutput;
    @Mock
    private GetProductsFilterGateway getProductsFilterGateway;

    @Captor
    private ArgumentCaptor<List<ErrorResponseModel>> errorCaptor;
    @Captor
    private ArgumentCaptor<List<GetProductsFilterResponseModel>> responseModelCaptor;

    private GetProductsFilterUseCase getProductsFilterUseCase;

    private Long id = Long.MAX_VALUE;
    private String name = "BIKE";
    private Double price = 12.5;
    private Double basicTax = 0.5;
    private Double additionalTax = 0.1;
    private ProductType productType = ProductType.BOOK;
    private String isImported = "N";

    @BeforeEach
    public void setup() {
        this.getProductsFilterUseCase = new GetProductsFilterUseCase(getProductsFilterOutput,
                getProductsFilterGateway);
    }

    @Test
    public void shouldReturnErrorWhenRequestModelIsNull() {
        final var exception= assertThrows(IllegalArgumentException.class,
                () -> getProductsFilterUseCase.execute(null));
    }

    @Test
    public void shouldReturnErrorWhenImportedValueIsInvalid() {
        final var request = GetProductsFilterRequestModel.builder()
                .isImported("INVALID")
                .build();

        this.getProductsFilterUseCase.execute(request);

        assertIfHasOnlyError(ErrorResponseModel.builder().code(CommonErrorCode.E0002.name())
                .message(CommonErrorCode.E0002.getValue()).build());
    }

    @Test
    public void shouldReturnEmptyListWhenGatewayReturnEmptyOrNull() {
        final var request = GetProductsFilterRequestModel.builder()
                .productName("XYZ")
                .build();

        when(this.getProductsFilterGateway.getProductsByFilter(
                "XYZ",
                null
        )).thenReturn(Collections.emptyList());

        this.getProductsFilterUseCase.execute(request);

        verify(this.getProductsFilterOutput, only()).success(
                this.responseModelCaptor.capture());
        verify(this.getProductsFilterOutput, never()).error(anyList());

        final var expectedResponse =this.responseModelCaptor.getValue();
        assertThat(expectedResponse).isEmpty();
    }

    private void assertIfHasOnlyError(ErrorResponseModel expectedResponseError) {
        verify(this.getProductsFilterOutput, never()).success(anyList());
        verify(this.getProductsFilterOutput, only()).error(errorCaptor.capture());
        final var errors = errorCaptor.getValue();
        assertThat(errors).containsOnly(expectedResponseError);
    }

}
