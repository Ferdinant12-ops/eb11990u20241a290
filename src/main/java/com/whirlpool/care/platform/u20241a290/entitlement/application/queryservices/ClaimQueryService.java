package com.whirlpool.care.platform.u20241a290.entitlement.application.queryservices;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates.Claim;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.queries.GetClaimByIdQuery;

import java.util.Optional;

/**
 * Query service for the Claim aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
public interface ClaimQueryService {
    Optional<Claim> handle(GetClaimByIdQuery query);
}
