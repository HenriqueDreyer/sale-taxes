package com.dreyer.saletaxes.web.controller;

import com.dreyer.saletaxes.core.boundary.requestmodel.GetProductsFilterRequestModel;
import com.dreyer.saletaxes.web.presenter.GetProductsFilterPresenter;
import com.dreyer.saletaxes.web.service.GetProductsFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class GetProductsFilterController {
    private final GetProductsFilterService getProductsFilterService;
    private final GetProductsFilterPresenter getProductsFilterPresenter;

    @Autowired
    public GetProductsFilterController(GetProductsFilterService getProductsFilterService,
                                       GetProductsFilterPresenter getProductsFilterPresenter) {
        this.getProductsFilterPresenter = getProductsFilterPresenter;
        this.getProductsFilterService = getProductsFilterService;
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProductsFilter(
            @RequestParam(value = "name", required = false) String productName,
            @RequestParam(value = "isImported", required = false) boolean isImported
    ) {
        this.getProductsFilterService.execute(GetProductsFilterRequestModel.builder()
                .productName(productName)
                .isImported(isImported)
                .build());

        return this.getProductsFilterPresenter.present();
    }
}
