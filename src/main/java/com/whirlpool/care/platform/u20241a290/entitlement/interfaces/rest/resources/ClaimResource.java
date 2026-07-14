package com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * REST response resource for a Claim. Excludes audit attributes.
 *
 * @author Jose Fernando Flores Pinchi
 */
public record ClaimResource(
        Long id,
        Long policyId,
        String issueDescription,
        String claimStatus,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime reportedAt) {
}
