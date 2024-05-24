package com.contentgrid.hateoas.client.hal;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

@Data
public class HalDocument {

    @JsonProperty("_links")
    @JsonFormat(with = Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    Map<String, List<HalLink>> links = new HashMap<>();

    @JsonProperty("_embedded")
    protected Map<String, List<HalDocument>> embedded = new HashMap<>();


    Map<String, Object> fields = new LinkedHashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getFields() {
        return this.fields;
    }

    @JsonAnySetter
    public void setFields(String name, Object value) {
        this.fields.put(name, value);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T getField(String name) {
        return (T) this.fields.get(name);
    }

    @NonNull
    public HalLink getSelfLink() {
        return this.getLink("self").orElseThrow();
    }

    public Optional<HalLink> getLink(@NonNull String name) {
        return Optional.ofNullable(this.links.get(name))
                .flatMap(links -> {
                    if (links.size() == 0) {
                        return Optional.empty();
                    } else if (links.size() == 1) {
                        return links.stream().findFirst();
                    }
                    throw new RuntimeException("Expected 1 link, but has %s links".formatted(links.size()));
                });
    }

    public Optional<List<HalLink>> getLinks(@NonNull String name) {
        return Optional.ofNullable(this.links.get(name));
    }

    public HalLink getRequiredLink(@NonNull String name) {
        return this.getLink(name)
                .orElseThrow(() -> new NoSuchElementException("Link with name %s not found".formatted(name)));
    }

    public Optional<List<HalDocument>> getEmbedded(@NonNull String name) {
        return Optional.ofNullable(this.embedded.get(name));
    }

    public List<HalDocument> getEmbeddedOrEmpty(@NonNull String name) {
        return this.getEmbedded(name).orElse(List.of());
    }
}
