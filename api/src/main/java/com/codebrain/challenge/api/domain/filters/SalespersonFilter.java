package com.codebrain.challenge.api.domain.filters;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

@Introspected
public class SalespersonFilter {

    @Nullable
    private String registration;

    public SalespersonFilter() {

    }

    public SalespersonFilter(String registration) {
        this.registration = registration;
    }

    @Nullable
    public String getRegistration() {
        return registration;
    }
}
