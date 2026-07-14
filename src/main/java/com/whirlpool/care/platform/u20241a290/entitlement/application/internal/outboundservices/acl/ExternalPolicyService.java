package com.whirlpool.care.platform.u20241a290.entitlement.application.internal.outboundservices.acl;

import com.whirlpool.care.platform.u20241a290.contracts.interfaces.acl.ContractsContextFacade;
import org.springframework.stereotype.Service;

/**
 * Outbound Anti-Corruption Layer service used by the entitlement context to access the
 * contracts context through its facade, without coupling to its internal model.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Service
public class ExternalPolicyService {

    private final ContractsContextFacade contractsContextFacade;

    public ExternalPolicyService(ContractsContextFacade contractsContextFacade) {
        this.contractsContextFacade = contractsContextFacade;
    }

    public boolean existsPolicy(Long policyId) {
        return this.contractsContextFacade.existsPolicyById(policyId);
    }

    public boolean isCoverageExpired(Long policyId) {
        return this.contractsContextFacade.isCoverageExpired(policyId);
    }
}
