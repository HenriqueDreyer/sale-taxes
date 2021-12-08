package com.dreyer.saletaxes.web.controller;

import com.dreyer.saletaxes.core.boundary.requestmodel.GetProductsFilterRequestModel;
import com.dreyer.saletaxes.core.domain.Product;
import com.dreyer.saletaxes.web.presenter.GetProductsFilterPresenter;
import com.dreyer.saletaxes.web.service.GetProductsFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetProductsFilterControllerTest {

    @Mock
    private GetProductsFilterService getProductsFilterService;

    @Mock
    GetProductsFilterPresenter getProductsFilterPresenter;

    @Captor
    private ArgumentCaptor<GetProductsFilterRequestModel> productsFilterRequestModelArgumentCaptor;

    private GetProductsFilterController getProductsFilterController;

    private Long id = Long.MAX_VALUE;
    private String name = "BIKE";
    private Long price = Long.MAX_VALUE;
    private boolean isImported = false;

    @BeforeEach
    public void setup() {
        this.getProductsFilterController = new GetProductsFilterController(getProductsFilterService, getProductsFilterPresenter);
    }

    @Test
    public void shouldGetProductsFilterCorrectly() {
        final var product = Product.builder()
                .id(Long.MAX_VALUE)
                .name("BIKE")
                .price(Long.MAX_VALUE)
                .isImported(true)
                .build();

        final ResponseEntity<Object> responseEntity = ResponseEntity.ok(product);

        when(this.getProductsFilterPresenter.present()).thenReturn(responseEntity);

        //When
        var response = this.getProductsFilterController.getProductsFilter(
                name, isImported
        );

        //Then
        verify(this.getProductsFilterService, only()).execute(
                this.productsFilterRequestModelArgumentCaptor.capture()
        );
        verify(this.getProductsFilterPresenter, only()).present();

        final var getProductsFilterRequestModel =
                this.productsFilterRequestModelArgumentCaptor.getValue();

        assertThat(getProductsFilterRequestModel).isNotNull();
        assertThat(getProductsFilterRequestModel.getProductName()).isNotNull();

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(product);
    }
}
