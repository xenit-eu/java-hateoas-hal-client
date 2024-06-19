package com.contentgrid.hateoas.client.hal;

import java.time.Instant;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.client.endpoint.DefaultClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2ClientCredentialsGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

public class OAuth2TokenService {

    private final ClientRegistration clientRegistration;

    @Nullable
    private OAuth2AccessToken accessToken;

    private final DefaultClientCredentialsTokenResponseClient tokenClient;

    public OAuth2TokenService(ClientRegistration clientRegistration) {
        this.clientRegistration = clientRegistration;
        this.tokenClient = new DefaultClientCredentialsTokenResponseClient();
    }

    public OAuth2AccessToken getAccessToken() {
        if (accessToken == null || Instant.now().isAfter(accessToken.getExpiresAt().minusSeconds(60))) {
            refreshAccessToken();
        }
        return accessToken;
    }

    private void refreshAccessToken() {
        var request = new OAuth2ClientCredentialsGrantRequest(clientRegistration);
        var response = tokenClient.getTokenResponse(request);
        accessToken = response.getAccessToken();
    }
}
