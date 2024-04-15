package com.contentgrid.hateoas.client.hal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient.ResponseSpec;

@RequiredArgsConstructor
class DefaultHalResponse implements HalResponse {

    @NonNull
    private final ResponseSpec response;


    @Override
    public <T> ResponseEntity<T> toEntity(Class<T> bodyType) {
        return response.toEntity(bodyType);
    }
}
