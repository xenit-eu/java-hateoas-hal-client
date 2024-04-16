package com.contentgrid.hateoas.client.hal.forms;

import com.contentgrid.hateoas.client.hal.HalClient;
import com.contentgrid.hateoas.client.hal.HalDocument;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.web.client.RestClient;

public interface HalFormsClient extends HalClient {

    /**
     * Obtain a {@code HalFormsClient.Builder}.
     */
    static HalFormsClient.Builder builder() {
        return new DefaultHalFormsClientBuilder();
    }

    Optional<HalFormsTemplate> getTemplate(@NonNull HalDocument document, @NonNull String name);

    default HalFormsTemplate getRequiredTemplate(@NonNull HalDocument document, @NonNull String name) {
        return this.getTemplate(document, name)
                .orElseThrow(() -> new NoSuchElementException("Template with name %s not found".formatted(name)));
    }

    HalFormsBodyRequest requestTemplate(@NonNull HalDocument document, @NonNull String templateName);

    default HalFormsBodyRequest requestDefaultTemplate(@NonNull HalDocument document) {
        return this.requestTemplate(document, "default");
    }

    /**
     * A mutable builder for creating a {@link HalFormsClient}.
     */
    interface Builder {
        Builder restClient(RestClient restClient);

        /**
         * Build the {@link HalFormsClient} instance.
         */
        HalFormsClient build();
    }

}
