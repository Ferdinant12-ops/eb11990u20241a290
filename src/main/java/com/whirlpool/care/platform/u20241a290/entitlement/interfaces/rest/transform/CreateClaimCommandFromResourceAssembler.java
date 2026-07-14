package com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.transform;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.commands.CreateClaimCommand;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.valueobjects.ClaimStatus;
import com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.resources.CreateClaimResource;

/**
 * Assembler that builds a CreateClaimCommand from a CreateClaimResource.
 *
 * @author Jose Fernando Flores Pinchi
 */
public class CreateClaimCommandFromResourceAssembler {

    private CreateClaimCommandFromResourceAssembler() {
    }

    public static CreateClaimCommand toCommandFromResource(CreateClaimResource resource) {
        return new CreateClaimCommand(
                resource.policyId(),
                resource.issueDescription(),
                ClaimStatus.fromString(resource.claimStatus()),
                resource.reportedAt());
    }
}
