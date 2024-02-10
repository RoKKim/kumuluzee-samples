package com.kumuluz.ee.samples.graphql_advanced.utils;

import com.kumuluz.ee.rest.utils.QueryStringDefaults;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

public class ApplicationDefaults {

    @Produces
    @ApplicationScoped
    public QueryStringDefaults getQueryDefaults() {
        return new QueryStringDefaults()
                .defaultOffset(0)
                .defaultLimit(20)
                .maxLimit(100);
    }
}
