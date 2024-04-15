package com.contentgrid.hateoas.client.hal.forms;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.client.RestClient;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DefaultHalFormsClientBuilder implements HalFormsClient.Builder {

    private RestClient restClient;

    @Override
    public HalFormsClient build() {
        return new DefaultHalFormsClient(this.restClient);
    }
}
