package com.whirlpool.care.platform.u20241a290.contracts.interfaces.rest.transform;

import com.whirlpool.care.platform.u20241a290.contracts.domain.model.aggregates.Policy;
import com.whirlpool.care.platform.u20241a290.contracts.interfaces.rest.resources.PolicyResource;

/**
 * Assembler that builds a PolicyResource from a Policy aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
public class PolicyResourceFromEntityAssembler {

    private PolicyResourceFromEntityAssembler() {
    }

    public static PolicyResource toResourceFromEntity(Policy policy) {
        return new PolicyResource(
                policy.getId(),
                policy.getPolicyNumber(),
                policy.getApplianceModel(),
                policy.getCoveragePeriod().startDate(),
                policy.getCoveragePeriod().endDate(),
                policy.coverageStatus().name(),
                policy.getLastClaimId(),
                policy.isEligibleForClaim());
    }
}
