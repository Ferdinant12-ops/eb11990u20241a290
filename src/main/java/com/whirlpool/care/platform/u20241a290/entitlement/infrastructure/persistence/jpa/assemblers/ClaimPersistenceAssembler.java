package com.whirlpool.care.platform.u20241a290.entitlement.infrastructure.persistence.jpa.assemblers;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates.Claim;
import com.whirlpool.care.platform.u20241a290.entitlement.infrastructure.persistence.jpa.entities.ClaimPersistenceEntity;
import org.springframework.stereotype.Component;

/**
 * Assembler translating between the Claim aggregate and its persistence entity.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Component
public class ClaimPersistenceAssembler {

    public ClaimPersistenceEntity toNewEntity(Claim claim) {
        var entity = new ClaimPersistenceEntity();
        entity.setPolicyId(claim.getPolicyId());
        entity.setIssueDescription(claim.getIssueDescription());
        entity.setClaimStatus(claim.getClaimStatus());
        entity.setReportedAt(claim.getReportedAt());
        return entity;
    }

    public Claim toDomain(ClaimPersistenceEntity entity) {
        var claim = new Claim();
        claim.setId(entity.getId());
        claim.setPolicyId(entity.getPolicyId());
        claim.setIssueDescription(entity.getIssueDescription());
        claim.setClaimStatus(entity.getClaimStatus());
        claim.setReportedAt(entity.getReportedAt());
        return claim;
    }
}
