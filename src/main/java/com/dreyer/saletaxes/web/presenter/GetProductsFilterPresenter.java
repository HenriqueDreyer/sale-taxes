package com.dreyer.saletaxes.web.presenter;

import com.dreyer.saletaxes.core.boundary.output.GetProductsFilterOutput;
import com.dreyer.saletaxes.core.boundary.responsemodel.ErrorResponseModel;
import com.dreyer.saletaxes.core.boundary.responsemodel.GetProductsFilterResponseModel;
import com.dreyer.saletaxes.web.dto.GetProductsFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetProductsFilterPresenter extends BasePresenter<List<GetProductsFilterResponseModel>,
        List<GetProductsFilterDTO>> implements GetProductsFilterOutput {

    @Autowired
    public GetProductsFilterPresenter() {
        super();
    }

    @Override
    protected ResponseEntity<Object> presentOnError(List<ErrorResponseModel> errors) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.buildErrorResponseBody(errors));
    }

    @Override
    protected List<GetProductsFilterDTO> convert(List<GetProductsFilterResponseModel> responseModel) {
        return responseModel.stream().map(GetProductsFilterDTO::buildFromModel)
                .collect(Collectors.toList());
    }
}
