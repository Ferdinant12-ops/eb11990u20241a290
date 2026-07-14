package com.whirlpool.care.platform.u20241a290.contracts.domain.repositories;

import com.whirlpool.care.platform.u20241a290.contracts.domain.model.aggregates.Policy;

import java.util.List;
import java.util.Optional;

/**
 * Domain repository abstraction for the Policy aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
public interface PolicyRepository {
    Policy save(Policy policy);

    Optional<Policy> findById(Long id);

    List<Policy> findAll();

    boolean existsByPolicyNumber(String policyNumber);

    long count();
}
