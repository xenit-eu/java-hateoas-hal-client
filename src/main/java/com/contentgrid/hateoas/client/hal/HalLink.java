package com.contentgrid.hateoas.client.hal;

import java.net.URI;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HalLink {

    @Getter
    @NonNull
    String href;

    public URI getURI() {
        return URI.create(this.href);
    }

    public static HalLink from(@NonNull URI uri) {
        return new HalLink(uri.toString());
    }

    @Override
    public String toString() {
        return "{href=%s}".formatted(this.href);
    }
}
