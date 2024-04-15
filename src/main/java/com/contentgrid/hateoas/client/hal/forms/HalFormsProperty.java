package com.contentgrid.hateoas.client.hal.forms;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

@Data
public class HalFormsProperty {

    String name;
    String type;
    String prompt;
    String regex;
    boolean readOnly = false;
    boolean required = false;
    boolean templated = false;

    Map<String, Object> properties = new LinkedHashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    @JsonAnySetter
    public void setProperty(String name, Object value) {
        this.properties.put(name, value);
    }

    @Override
    public String toString() {
        return "property:{name=%s, type=%s, prompt=%s, regex=%s, readOnly=%s, required=%s, templated=%s, props=%s}"
                .formatted(name, type, prompt, regex, readOnly, required, templated, properties);
    }
}
