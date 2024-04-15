package com.contentgrid.hateoas.client.hal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient.RequestHeadersSpec;

@Slf4j
@RequiredArgsConstructor
class DefaultHalRequest implements HalRequest {

    private final RequestHeadersSpec<? extends RequestHeadersSpec<?>> request;

    @Override
    public HalResponse execute() {
        request.httpRequest(request -> log.info("HTTP {} {}",request.getMethod(), request.getURI()));

        var response = request.retrieve();

        return new DefaultHalResponse(response);
    }
}
