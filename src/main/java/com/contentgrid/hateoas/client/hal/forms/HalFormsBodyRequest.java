package com.contentgrid.hateoas.client.hal.forms;

import com.contentgrid.hateoas.client.hal.HalRequest;
import java.util.Map;
import java.util.function.Function;

public interface HalFormsBodyRequest extends HalRequest {

    HalRequest properties(Function<HalFormsProperty, Object> valueFunction);

    default HalRequest properties(Map<String, Object> values) {
        return this.properties(property -> values.get(property.getName()));
    }
}
