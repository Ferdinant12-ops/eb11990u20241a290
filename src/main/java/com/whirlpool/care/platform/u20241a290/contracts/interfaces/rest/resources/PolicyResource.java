package com.whirlpool.care.platform.u20241a290.contracts.interfaces.rest.resources;

import java.time.LocalDate;

/**
 * REST response resource for a Policy. Excludes audit attributes; exposes the calculated
 * coverage status and claim eligibility.
 *
 * @author Jose Fernando Flores Pinchi
 */
public record PolicyResource(
        Long id,
        String policyNumber,
        String applianceModel,
        LocalDate coverageStartDate,
        LocalDate coverageEndDate,
        String coverageStatus,
        Long lastClaimId,
        boolean eligibleForClaim) {
}
