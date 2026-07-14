package com.whirlpool.care.platform.u20241a290.contracts.application.internal.queryservices;

import com.whirlpool.care.platform.u20241a290.contracts.application.queryservices.PolicyQueryService;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.aggregates.Policy;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.queries.GetAllPoliciesQuery;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.queries.GetPolicyByIdQuery;
import com.whirlpool.care.platform.u20241a290.contracts.domain.repositories.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Query service implementation for the Policy aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Service
public class PolicyQueryServiceImpl implements PolicyQueryService {

    private final PolicyRepository policyRepository;

    public PolicyQueryServiceImpl(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<Policy> handle(GetAllPoliciesQuery query) {
        return this.policyRepository.findAll();
    }

    @Override
    public Optional<Policy> handle(GetPolicyByIdQuery query) {
        return this.policyRepository.findById(query.policyId());
    }
}
