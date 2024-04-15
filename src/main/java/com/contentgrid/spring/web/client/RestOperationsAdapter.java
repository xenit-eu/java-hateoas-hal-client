package com.contentgrid.spring.web.client;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestBodySpec;
import org.springframework.web.client.RestClient.RequestHeadersSpec.ExchangeFunction;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

/**
 * See <a href="https://github.com/spring-projects/spring-framework/issues/32598">GitHub: Spring Framework issue
 * 32598</a>
 */
@RequiredArgsConstructor
public class RestOperationsAdapter implements RestOperations {

    @NonNull
    private final RestClient restClient;

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        return this.restClient.get().uri(url, uriVariables).retrieve().body(responseType);
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        return this.restClient.get().uri(url, uriVariables).retrieve().body(responseType);
    }

    @Override
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        return this.restClient.get().uri(url).retrieve().body(responseType);
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        return this.restClient.get().uri(url, uriVariables).retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        return this.restClient.get().uri(url, uriVariables).retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {
        return this.restClient.get().uri(url).retrieve().toEntity(responseType);
    }

    @Override
    public HttpHeaders headForHeaders(String url, Object... uriVariables) throws RestClientException {
        return this.restClient.head().uri(url, uriVariables).retrieve().toBodilessEntity().getHeaders();
    }

    @Override
    public HttpHeaders headForHeaders(String url, Map<String, ?> uriVariables) throws RestClientException {
        return this.restClient.head().uri(url, uriVariables).retrieve().toBodilessEntity().getHeaders();
    }

    @Override
    public HttpHeaders headForHeaders(URI url) throws RestClientException {
        return this.restClient.head().uri(url).retrieve().toBodilessEntity().getHeaders();
    }

    @Override
    public URI postForLocation(String url, @Nullable Object request, Object... uriVariables)
            throws RestClientException {
        var spec = this.restClient.post().uri(url, uriVariables);
        if (request != null) {
            spec = spec.body(request);
        }
        return spec.retrieve().toBodilessEntity().getHeaders().getLocation();
    }

    @Override
    public URI postForLocation(String url, @Nullable Object request, Map<String, ?> uriVariables)
            throws RestClientException {
        var spec = this.restClient.post().uri(url, uriVariables);
        if (request != null) {
            spec = spec.body(request);
        }
        return spec.retrieve().toBodilessEntity().getHeaders().getLocation();
    }

    @Override
    public URI postForLocation(URI url, @Nullable Object request) throws RestClientException {
        var spec = this.restClient.post().uri(url);
        if (request != null) {
            spec = spec.body(request);
        }
        return spec.retrieve().toBodilessEntity().getHeaders().getLocation();
    }

    @Override
    public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        var spec = this.restClient.post().uri(url, uriVariables);
        if (request != null) {
            spec = spec.body(request);
        }

        return spec.retrieve().body(responseType);
    }

    @Override
    public <T> T postForObject(String url, @Nullable Object request, Class<T> responseType, Map<String, ?> uriVariables)
            throws RestClientException {
        var spec = this.restClient.post().uri(url, uriVariables);
        if (request != null) {
            spec = spec.body(request);
        }

        return spec.retrieve().body(responseType);
    }

    @Override
    public <T> T postForObject(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException {
        var spec = this.restClient.post().uri(url);
        if (request != null) {
            spec.body(request);
        }
        return spec.retrieve().body(responseType);
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,
            Object... uriVariables) throws RestClientException {
        var spec = this.restClient.post().uri(url, uriVariables);
        if (request != null) {
            spec.body(request);
        }
        return spec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request, Class<T> responseType,
            Map<String, ?> uriVariables) throws RestClientException {
        var spec = this.restClient.post().uri(url, uriVariables);
        if (request != null) {
            spec.body(request);
        }
        return spec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(URI url, @Nullable Object request, Class<T> responseType)
            throws RestClientException {
        var spec = this.restClient.post().uri(url);
        if (request != null) {
            spec.body(request);
        }
        return spec.retrieve().toEntity(responseType);
    }

    @Override
    public void put(String url, @Nullable Object request, Object... uriVariables) throws RestClientException {
        var spec = this.restClient.post().uri(url, uriVariables);
        if (request != null) {
            spec.body(request);
        }
        spec.retrieve();
    }

    @Override
    public void put(String url, @Nullable Object request, Map<String, ?> uriVariables) throws RestClientException {
        var spec = this.restClient.post().uri(url, uriVariables);
        if (request != null) {
            spec.body(request);
        }
        spec.retrieve();
    }

    @Override
    public void put(URI url, @Nullable Object request) throws RestClientException {
        var spec = this.restClient.post().uri(url);
        if (request != null) {
            spec.body(request);
        }
        spec.retrieve();
    }

    @Override
    public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)
            throws RestClientException {
        var spec = this.restClient.patch().uri(url, uriVariables);
        if (request != null) {
            spec.body(request);
        }
        return spec.retrieve().body(responseType);
    }

    @Override
    public <T> T patchForObject(String url, @Nullable Object request, Class<T> responseType,
            Map<String, ?> uriVariables)
            throws RestClientException {
        var spec = this.restClient.patch().uri(url, uriVariables);
        if (request != null) {
            spec.body(request);
        }
        return spec.retrieve().body(responseType);
    }

    @Override
    public <T> T patchForObject(URI url, @Nullable Object request, Class<T> responseType) throws RestClientException {
        var spec = this.restClient.patch().uri(url);
        if (request != null) {
            spec.body(request);
        }
        return spec.retrieve().body(responseType);
    }

    @Override
    public void delete(String url, Object... uriVariables) throws RestClientException {
        this.restClient.delete().uri(url, uriVariables).retrieve().toBodilessEntity();
    }

    @Override
    public void delete(String url, Map<String, ?> uriVariables) throws RestClientException {
        this.restClient.delete().uri(url, uriVariables).retrieve().toBodilessEntity();
    }

    @Override
    public void delete(URI url) throws RestClientException {
        this.restClient.delete().uri(url).retrieve().toBodilessEntity();
    }

    @Override
    public Set<HttpMethod> optionsForAllow(String url, Object... uriVariables) throws RestClientException {
        return this.restClient.options().uri(url, uriVariables).retrieve().toBodilessEntity().getHeaders().getAllow();
    }

    @Override
    public Set<HttpMethod> optionsForAllow(String url, Map<String, ?> uriVariables) throws RestClientException {
        return this.restClient.options().uri(url, uriVariables).retrieve().toBodilessEntity().getHeaders().getAllow();
    }

    @Override
    public Set<HttpMethod> optionsForAllow(URI url) throws RestClientException {
        return this.restClient.options().uri(url).retrieve().toBodilessEntity().getHeaders().getAllow();
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
            Class<T> responseType, Object... uriVariables) throws RestClientException {
        var requestSpec = this.restClient.method(method).uri(url, uriVariables);
        requestSpec = applyRequestEntity(requestEntity, requestSpec);

        return requestSpec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
            Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        var requestSpec = this.restClient.method(method).uri(url, uriVariables);
        requestSpec = applyRequestEntity(requestEntity, requestSpec);

        return requestSpec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
            Class<T> responseType) throws RestClientException {
        var requestSpec = this.restClient.method(method).uri(url);
        requestSpec = applyRequestEntity(requestEntity, requestSpec);

        return requestSpec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
            ParameterizedTypeReference<T> responseType, Object... uriVariables) throws RestClientException {
        var requestSpec = this.restClient.method(method).uri(url, uriVariables);
        requestSpec = applyRequestEntity(requestEntity, requestSpec);

        return requestSpec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
            ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) throws RestClientException {
        var requestSpec = this.restClient.method(method).uri(url, uriVariables);
        requestSpec = applyRequestEntity(requestEntity, requestSpec);

        return requestSpec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
            ParameterizedTypeReference<T> responseType) throws RestClientException {
        var requestSpec = this.restClient.method(method).uri(url);
        requestSpec = applyRequestEntity(requestEntity, requestSpec);

        return requestSpec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(@NonNull RequestEntity<?> requestEntity, Class<T> responseType)
            throws RestClientException {
        var requestSpec = this.restClient
                .method(httpMethodOrDefault(requestEntity))
                .uri(requestEntity.getUrl());
        requestSpec = applyRequestEntity(requestEntity, requestSpec);

        return requestSpec.retrieve().toEntity(responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, ParameterizedTypeReference<T> responseType)
            throws RestClientException {
        var requestSpec = this.restClient
                .method(httpMethodOrDefault(requestEntity))
                .uri(requestEntity.getUrl());
        requestSpec = applyRequestEntity(requestEntity, requestSpec);

        return requestSpec.retrieve().toEntity(responseType);
    }

    @Override
    @Nullable
    public <T> T execute(String uriTemplate, HttpMethod method,
            @Nullable RequestCallback requestCallback,
            @Nullable ResponseExtractor<T> responseExtractor, Object... uriVariables) throws RestClientException {

        return this.restClient.method(method).uri(uriTemplate, uriVariables)
                .httpRequest(httpClientRequestCallback(requestCallback))
                .exchange(asExchangeFunction(responseExtractor), false)
                .orElse(null);
    }

    @Override
    public <T> T execute(String uriTemplate, HttpMethod method, @Nullable RequestCallback requestCallback,
            @Nullable ResponseExtractor<T> responseExtractor, Map<String, ?> uriVariables) throws RestClientException {
        return this.restClient.method(method).uri(uriTemplate, uriVariables)
                .httpRequest(httpClientRequestCallback(requestCallback))
                .exchange(asExchangeFunction(responseExtractor), false)
                .orElse(null);
    }

    @Override
    public <T> T execute(URI url, HttpMethod method, @Nullable RequestCallback requestCallback,
            @Nullable ResponseExtractor<T> responseExtractor) throws RestClientException {
        return this.restClient.method(method).uri(url)
                .httpRequest(httpClientRequestCallback(requestCallback))
                .exchange(asExchangeFunction(responseExtractor), false)
                .orElse(null);
    }

    private static RequestBodySpec applyRequestEntity(@Nullable HttpEntity<?> requestEntity, RequestBodySpec spec) {
        if (requestEntity != null) {
            // map headers from request-entity to headers
            spec = spec.headers(headers -> headers.putAll(requestEntity.getHeaders()));

            if (requestEntity.hasBody()) {
                spec = spec.body(Objects.requireNonNull(requestEntity.getBody()));
            }
        }
        return spec;
    }

    private static HttpMethod httpMethodOrDefault(@Nullable RequestEntity<?> requestEntity) {
        var defaultHttpMethod = HttpMethod.GET;

        if (requestEntity != null) {
            var method = requestEntity.getMethod();
            if (method != null) {
                return method;
            }
        }

        return defaultHttpMethod;
    }

    private static Consumer<ClientHttpRequest> httpClientRequestCallback(@Nullable RequestCallback requestCallback) {
        return clientRequest -> {
            try {
                if (requestCallback != null) {
                    requestCallback.doWithRequest(clientRequest);
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    private static <T> ExchangeFunction<Optional<T>> asExchangeFunction(
            @Nullable ResponseExtractor<T> responseExtractor) {
        return (clientRequest, clientResponse) -> {
            if (responseExtractor == null) {
                return Optional.empty();
            }

            return Optional.ofNullable(responseExtractor.extractData(clientResponse));
        };
    }
}
