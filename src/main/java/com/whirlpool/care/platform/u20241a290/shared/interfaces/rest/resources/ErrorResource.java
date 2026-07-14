package com.whirlpool.care.platform.u20241a290.shared.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jspecify.annotations.Nullable;

/**
 * Standard error response resource returned from REST endpoints.
 *
 * @author Jose Fernando Flores Pinchi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResource(String code, String message, @Nullable String details) {

    public ErrorResource(String code, String message) {
        this(code, message, null);
    }
}
