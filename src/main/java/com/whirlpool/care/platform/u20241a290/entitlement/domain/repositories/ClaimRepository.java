package com.whirlpool.care.platform.u20241a290.entitlement.domain.repositories;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates.Claim;

import java.util.Optional;

/**
 * Domain repository abstraction for the Claim aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
public interface ClaimRepository {
    Claim save(Claim claim);

    Optional<Claim> findById(Long id);
}
