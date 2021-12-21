package com.dreyer.saletaxes.web.controller;

import com.dreyer.saletaxes.core.boundary.requestmodel.GetProductsFilterRequestModel;
import com.dreyer.saletaxes.core.domain.entity.Product;
import com.dreyer.saletaxes.web.presenter.GetProductsFilterPresenter;
import com.dreyer.saletaxes.web.service.GetProductsFilterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
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

    @Operation(summary = "Get products list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return products list successfully",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Parameter"),
            @ApiResponse(responseCode = "404", description = "Products list not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProductsFilter(
            @RequestParam(value = "name", required = false) String productName,
            @RequestParam(value = "isImported", required = false) String isImported
    ) {
        this.getProductsFilterService.execute(GetProductsFilterRequestModel.builder()
                .productName(productName)
                .isImported(isImported)
                .build());

        return this.getProductsFilterPresenter.present();
    }
}
