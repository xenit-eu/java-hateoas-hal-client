package com.contentgrid.hateoas.client.hal;

import com.contentgrid.hateoas.client.hal.HalClient.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Data
@Accessors(fluent = true)
class DefaultHalClientBuilder implements HalClient.Builder {

    @NonNull
    RestClient restClient = RestClient.builder().build();

    ClientRegistration clientRegistration;

    @Override
    public HalClient build() {
        return new DefaultHalClient(ClientBuilderUtil.mutateRestClientWithClientRegistration(restClient, clientRegistration));
    }
}
