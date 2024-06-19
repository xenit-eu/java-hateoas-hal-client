package com.contentgrid.hateoas.client.hal;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

public class ClientBuilderUtil {

    public static RestClient mutateRestClientWithClientRegistration(RestClient restClient, ClientRegistration clientRegistration) {
        if (clientRegistration != null) {
            var tokenService = new OAuth2TokenService(clientRegistration);

            var oAuthRestClient = restClient.mutate()
                    .requestInterceptor((request, body, execution) -> {
                        try {
                            request.getHeaders().setBearerAuth(tokenService.getAccessToken().getTokenValue());
                        } catch (Exception e) {
                            throw new RestClientException("Failed to obtain access token", e);
                        }
                        return execution.execute(request, body);
                    }).build();

            return oAuthRestClient;
        }
        return restClient;
    }

}
