package com.contentgrid.hateoas.client.hal;

import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public interface HalResponse {

    <T> ResponseEntity<T> toEntity(Class<T> bodyType);

    default ResponseEntity<HalDocument> toHalEntity() {
        return this.toEntity(HalDocument.class);
    }

    default HalDocument toHalDocument() {
        return Objects.requireNonNull(this.toHalEntity().getBody());
    }
}
