package com.whirlpool.care.platform.u20241a290.contracts.application.internal.commandservices;

import com.whirlpool.care.platform.u20241a290.contracts.application.commandservices.PolicyCommandService;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.aggregates.Policy;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.commands.ProcessClaimCreatedCommand;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.commands.SeedPoliciesCommand;
import com.whirlpool.care.platform.u20241a290.contracts.domain.repositories.PolicyRepository;
import com.whirlpool.care.platform.u20241a290.shared.domain.model.valueobjects.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Command service implementation for the Policy aggregate: seeding and claim processing.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Service
public class PolicyCommandServiceImpl implements PolicyCommandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyCommandServiceImpl.class);
    private final PolicyRepository policyRepository;

    public PolicyCommandServiceImpl(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void handle(SeedPoliciesCommand command) {
        if (this.policyRepository.count() != 0) {
            return;
        }
        List<Policy> policies = List.of(
                new Policy("WHP-10001", "KitchenAid-Dishwasher",
                        new Period(LocalDate.of(2026, 1, 1), LocalDate.of(2027, 12, 31))),
                new Policy("WHP-10002", "Whirlpool-Refrigerator",
                        new Period(LocalDate.of(2026, 1, 1), LocalDate.of(2028, 6, 15))),
                new Policy("WHP-10003", "Maytag-Washer",
                        new Period(LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1))),
                new Policy("WHP-10004", "JennAir-Oven",
                        new Period(LocalDate.of(2026, 1, 1), LocalDate.of(2029, 1, 20)))
        );
        policies.forEach(this.policyRepository::save);
        LOGGER.info("Seeded {} policies into the catalog", policies.size());
    }

    /**
     * Processes a created claim against its policy inside a single transaction, ensuring
     * idempotency (skipping already-processed claimIds) and relying on optimistic locking
     * (the @Version column on the persistence entity) to prevent concurrent inconsistencies.
     */
    @Override
    @Transactional
    public void handle(ProcessClaimCreatedCommand command) {
        var policyOptional = this.policyRepository.findById(command.policyId());
        if (policyOptional.isEmpty()) {
            LOGGER.warn("Policy {} not found while processing claim {}", command.policyId(), command.claimId());
            return;
        }
        var policy = policyOptional.get();
        if (command.claimId().equals(policy.getLastClaimId())) {
            LOGGER.info("Claim {} already processed for policy {} (idempotent skip)", command.claimId(), command.policyId());
            return;
        }
        policy.assignLastClaim(command.claimId());
        this.policyRepository.save(policy);
        LOGGER.info("Policy {} updated with lastClaimId {}", command.policyId(), command.claimId());
    }
}
