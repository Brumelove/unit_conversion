package com.flexion.converter.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponseBody {
    private String response;
}
