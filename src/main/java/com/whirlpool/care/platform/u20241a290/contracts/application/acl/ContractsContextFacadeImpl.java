package com.whirlpool.care.platform.u20241a290.contracts.application.acl;

import com.whirlpool.care.platform.u20241a290.contracts.application.queryservices.PolicyQueryService;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.queries.GetPolicyByIdQuery;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.valueobjects.CoverageStatus;
import com.whirlpool.care.platform.u20241a290.contracts.interfaces.acl.ContractsContextFacade;
import org.springframework.stereotype.Service;

/**
 * Implementation of the contracts ACL facade backed by the Policy query service.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Service
public class ContractsContextFacadeImpl implements ContractsContextFacade {

    private final PolicyQueryService policyQueryService;

    public ContractsContextFacadeImpl(PolicyQueryService policyQueryService) {
        this.policyQueryService = policyQueryService;
    }

    @Override
    public boolean existsPolicyById(Long policyId) {
        return this.policyQueryService.handle(new GetPolicyByIdQuery(policyId)).isPresent();
    }

    @Override
    public boolean isCoverageExpired(Long policyId) {
        return this.policyQueryService.handle(new GetPolicyByIdQuery(policyId))
                .map(policy -> policy.coverageStatus() == CoverageStatus.EXPIRED)
                .orElse(false);
    }
}
