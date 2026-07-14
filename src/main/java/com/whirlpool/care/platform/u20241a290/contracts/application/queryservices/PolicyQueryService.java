package com.whirlpool.care.platform.u20241a290.contracts.application.queryservices;

import com.whirlpool.care.platform.u20241a290.contracts.domain.model.aggregates.Policy;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.queries.GetAllPoliciesQuery;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.queries.GetPolicyByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Query service for the Policy aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
public interface PolicyQueryService {
    List<Policy> handle(GetAllPoliciesQuery query);

    Optional<Policy> handle(GetPolicyByIdQuery query);
}
