package com.contentgrid.hateoas.client.hal;

import java.net.URI;
import java.util.Objects;
import lombok.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestClient;

public interface HalClient {

    /**
     * Obtain a {@code RestClient} builder.
     */
    static HalClient.Builder builder() {
        return new DefaultHalClientBuilder();
    }

    HalRequest get(@NonNull URI uri);

    default HalRequest get(@NonNull HalLink link) {
        return this.get(link.getURI());
    }

    @Nullable
    default HalDocument follow(HalLink link) {
        var entity = this.get(link).toHalEntity();
        if (entity.getStatusCode().is3xxRedirection()) {
            URI location = link.getURI().resolve(Objects.requireNonNull(entity.getHeaders().getLocation()));
            if (link.getURI().normalize().equals(location.normalize())) {
                throw new RuntimeException("HTTP 3xx redirect loop to %s".formatted(location));
            }
            return this.follow(HalLink.from(location));
        }

        return entity.getBody();
    }

    /**
     * A mutable builder for creating a {@link HalClient}.
     */
    interface Builder {

        Builder restClient(RestClient restClient);

        /**
         * Build the {@link HalClient} instance.
         */
        HalClient build();
    }

}
