package com.contentgrid.hateoas.client.hal;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.springframework.web.client.RestClient;

@Data
@Accessors(fluent = true)
class DefaultHalClientBuilder implements HalClient.Builder {

    @NonNull
    RestClient restClient = RestClient.builder().build();

    @Override
    public HalClient build() {
        return new DefaultHalClient(restClient);
    }
}
