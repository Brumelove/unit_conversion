package com.flexion.converter.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConversionApiException extends RuntimeException {

    private ErrorResponse errorResponse;
}
