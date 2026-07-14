package com.whirlpool.care.platform.u20241a290.entitlement.application.internal.queryservices;

import com.whirlpool.care.platform.u20241a290.entitlement.application.queryservices.ClaimQueryService;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates.Claim;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.queries.GetClaimByIdQuery;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.repositories.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Query service implementation for the Claim aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Service
public class ClaimQueryServiceImpl implements ClaimQueryService {

    private final ClaimRepository claimRepository;

    public ClaimQueryServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public Optional<Claim> handle(GetClaimByIdQuery query) {
        return this.claimRepository.findById(query.claimId());
    }
}
