package com.whirlpool.care.platform.u20241a290.contracts.infrastructure.persistence.jpa.assemblers;

import com.whirlpool.care.platform.u20241a290.contracts.domain.model.aggregates.Policy;
import com.whirlpool.care.platform.u20241a290.contracts.infrastructure.persistence.jpa.entities.PolicyPersistenceEntity;
import com.whirlpool.care.platform.u20241a290.shared.domain.model.valueobjects.Period;
import org.springframework.stereotype.Component;

/**
 * Assembler translating between the Policy aggregate and its persistence entity.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Component
public class PolicyPersistenceAssembler {

    public PolicyPersistenceEntity toNewEntity(Policy policy) {
        var entity = new PolicyPersistenceEntity();
        entity.setPolicyNumber(policy.getPolicyNumber());
        entity.setApplianceModel(policy.getApplianceModel());
        entity.setCoverageStartDate(policy.getCoveragePeriod().startDate());
        entity.setCoverageEndDate(policy.getCoveragePeriod().endDate());
        entity.setLastClaimId(policy.getLastClaimId());
        return entity;
    }

    public Policy toDomain(PolicyPersistenceEntity entity) {
        var policy = new Policy(entity.getPolicyNumber(), entity.getApplianceModel(),
                new Period(entity.getCoverageStartDate(), entity.getCoverageEndDate()));
        policy.setId(entity.getId());
        policy.assignLastClaim(entity.getLastClaimId());
        return policy;
    }
}
