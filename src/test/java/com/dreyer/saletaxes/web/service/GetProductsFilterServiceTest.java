package com.dreyer.saletaxes.web.service;

import com.dreyer.saletaxes.core.boundary.input.GetProductsFilterInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetProductsFilterServiceTest {

    @Mock
    private GetProductsFilterInput getProductsFilterInput;

    private GetProductsFilterService getProductsFilterService;

    @BeforeEach
    public void setup() {
        this.getProductsFilterService = new GetProductsFilterService(getProductsFilterInput);
    }
}
