package com.contentgrid.hateoas.client.hal.forms;

import com.contentgrid.hateoas.client.hal.ClientBuilderUtil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.web.client.RestClient;

@Data
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class DefaultHalFormsClientBuilder implements HalFormsClient.Builder {

    private RestClient restClient;

    private ClientRegistration clientRegistration;

    @Override
    public HalFormsClient build() {
        return new DefaultHalFormsClient(
                ClientBuilderUtil.mutateRestClientWithClientRegistration(restClient, clientRegistration));
    }
}
