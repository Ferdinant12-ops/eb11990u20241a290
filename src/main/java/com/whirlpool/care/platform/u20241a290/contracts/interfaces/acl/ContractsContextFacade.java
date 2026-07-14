package com.whirlpool.care.platform.u20241a290.contracts.interfaces.acl;

/**
 * Anti-Corruption Layer facade exposing contracts context capabilities to other bounded contexts.
 *
 * @author Jose Fernando Flores Pinchi
 */
public interface ContractsContextFacade {

    /**
     * Checks whether a policy with the given identifier exists.
     *
     * @param policyId the policy identifier
     * @return true if the policy exists
     */
    boolean existsPolicyById(Long policyId);

    /**
     * Checks whether the coverage of the given policy is currently expired.
     *
     * @param policyId the policy identifier
     * @return true if the policy exists and its calculated coverage status is EXPIRED
     */
    boolean isCoverageExpired(Long policyId);
}
