package com.codebrain.challenge.api.domain.filters;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Nullable;

@Introspected
public class ProductFilter {

    @Nullable
    private Long id;

    @Nullable
    private String name;

    public ProductFilter() {

    }

    public ProductFilter(@Nullable Long id, @Nullable String name) {
        this.id = id;
        this.name = name;
    }

    @Nullable
    public Long getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }
}
