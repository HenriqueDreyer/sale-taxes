package com.dreyer.saletaxes.core.boundary.input;

import com.dreyer.saletaxes.core.boundary.requestmodel.GetProductsFilterRequestModel;

public interface GetProductsFilterInput {
    void execute(final GetProductsFilterRequestModel getProductsFilterRequestModel);
}
