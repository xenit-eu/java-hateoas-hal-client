package com.contentgrid.hateoas.client.hal;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public interface HalRequest {

    HalResponse execute();

    default ResponseEntity<HalDocument> toHalEntity() {
        return this.execute().toEntity(HalDocument.class);
    }

    @Nullable
    default HalDocument toHalDocument() {
        return this.execute().toHalDocument();
    }
}
