package com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.transform;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates.Claim;
import com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.resources.ClaimResource;

/**
 * Assembler that builds a ClaimResource from a Claim aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
public class ClaimResourceFromEntityAssembler {

    private ClaimResourceFromEntityAssembler() {
    }

    public static ClaimResource toResourceFromEntity(Claim claim) {
        return new ClaimResource(
                claim.getId(),
                claim.getPolicyId(),
                claim.getIssueDescription(),
                claim.getClaimStatus().name(),
                claim.getReportedAt());
    }
}
