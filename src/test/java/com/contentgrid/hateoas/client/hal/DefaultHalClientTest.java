package com.contentgrid.hateoas.client.hal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DefaultHalClientTest {

    @Test
    void halClient() {
        var client = HalClient.builder().build();
    }
}