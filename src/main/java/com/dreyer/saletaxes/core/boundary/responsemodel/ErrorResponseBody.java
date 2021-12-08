package com.dreyer.saletaxes.core.boundary.responsemodel;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class ErrorResponseBody {
    List<String> errors;
}
