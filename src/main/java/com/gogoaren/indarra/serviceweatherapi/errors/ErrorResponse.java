package com.gogoaren.indarra.serviceweatherapi.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
@Builder
public class ErrorResponse {

    private final int status;
    private final String message;
    private final Instant time;
    private final String path;
}
