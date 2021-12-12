package com.dreyer.saletaxes.core.boundary.output;

import com.dreyer.saletaxes.core.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.GetProductsFilterResponseModel;

import java.util.List;

public interface GetProductsFilterOutput {
    void success(List<GetProductsFilterResponseModel> responseModelList);
    void error(List<ErrorResponseModel> errors);
}
