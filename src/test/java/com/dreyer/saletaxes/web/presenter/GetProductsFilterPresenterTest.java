package com.dreyer.saletaxes.web.presenter;

import com.dreyer.saletaxes.core.boundary.responsemodel.ErrorResponseBody;
import com.dreyer.saletaxes.core.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.GetProductsFilterResponseModel;
import com.dreyer.saletaxes.core.domain.entity.ProductType;
import com.dreyer.saletaxes.web.dto.GetProductsFilterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductsFilterPresenterTest {

    @Mock
    private MessageSource messageSource;

    private GetProductsFilterPresenter getProductsFilterPresenter;

    private Long id = Long.MAX_VALUE;
    private String name = "BIKE";
    private String description = "Description";
    private Double price = Double.MAX_VALUE;
    private String isImported = "N";
    private Locale locale = Locale.US;

    @BeforeEach
    public void setup() {
        this.getProductsFilterPresenter = new GetProductsFilterPresenter(messageSource);
    }

    @Test
    public void shouldPresentCorrectlyWhenSuccess() {
        // Given
        final var productsResponseModel =
                Collections.singletonList(GetProductsFilterResponseModel.builder()
                        .id(id)
                        .name(name)
                        .description(description)
                        .price(price)
                        .productType(ProductType.OTHER)
                        .isImported(isImported)
                        .build());

        final var expectedResponse = productsResponseModel.stream()
                .map(GetProductsFilterDTO::buildFromModel)
                .collect(Collectors.toList());

        this.getProductsFilterPresenter.success((List<GetProductsFilterResponseModel>) productsResponseModel);

        // When
        final var response = this.getProductsFilterPresenter.present();

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    public void shouldPresenterOnErrorWithStatus400() {
        // Given
        final var httpStatus = HttpStatus.BAD_REQUEST;

        final var errorCode = "ESS0102";
        final var errorMessage = "Error ESS0102";
        final var params = Collections.emptyList();
        final var fullErrorMessage = String.format("%s - %s", errorCode, errorMessage);

        final var errors = Collections.singletonList(ErrorResponseModel.builder()
                .code(errorCode)
                .message(errorMessage)
                .build());

        final var expectedResponseBody = ErrorResponseBody.builder()
                .errors(Collections.singletonList(fullErrorMessage))
                .build();

        when(this.messageSource.getMessage(errorCode, params.toArray(), locale)).thenReturn(errorMessage);

        // When
        final var responseEntity = this.getProductsFilterPresenter.presentOnError(errors);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(httpStatus);
        assertThat(responseEntity.getBody()).isEqualTo(expectedResponseBody);

    }
}
