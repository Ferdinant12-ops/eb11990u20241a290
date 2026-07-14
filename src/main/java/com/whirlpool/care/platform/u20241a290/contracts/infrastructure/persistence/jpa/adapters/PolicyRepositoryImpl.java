package com.whirlpool.care.platform.u20241a290.contracts.infrastructure.persistence.jpa.adapters;

import com.whirlpool.care.platform.u20241a290.contracts.domain.model.aggregates.Policy;
import com.whirlpool.care.platform.u20241a290.contracts.domain.repositories.PolicyRepository;
import com.whirlpool.care.platform.u20241a290.contracts.infrastructure.persistence.jpa.assemblers.PolicyPersistenceAssembler;
import com.whirlpool.care.platform.u20241a290.contracts.infrastructure.persistence.jpa.repositories.PolicyPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository adapter implementing the domain PolicyRepository over Spring Data JPA.
 * On update it loads the managed entity so the optimistic-locking version is honored.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Repository
public class PolicyRepositoryImpl implements PolicyRepository {

    private final PolicyPersistenceRepository jpaRepository;
    private final PolicyPersistenceAssembler assembler;

    public PolicyRepositoryImpl(PolicyPersistenceRepository jpaRepository, PolicyPersistenceAssembler assembler) {
        this.jpaRepository = jpaRepository;
        this.assembler = assembler;
    }

    @Override
    public Policy save(Policy policy) {
        if (policy.getId() != null) {
            var managed = this.jpaRepository.findById(policy.getId())
                    .orElseGet(() -> this.assembler.toNewEntity(policy));
            managed.setPolicyNumber(policy.getPolicyNumber());
            managed.setApplianceModel(policy.getApplianceModel());
            managed.setCoverageStartDate(policy.getCoveragePeriod().startDate());
            managed.setCoverageEndDate(policy.getCoveragePeriod().endDate());
            managed.setLastClaimId(policy.getLastClaimId());
            return this.assembler.toDomain(this.jpaRepository.save(managed));
        }
        var created = this.jpaRepository.save(this.assembler.toNewEntity(policy));
        return this.assembler.toDomain(created);
    }

    @Override
    public Optional<Policy> findById(Long id) {
        return this.jpaRepository.findById(id).map(this.assembler::toDomain);
    }

    @Override
    public List<Policy> findAll() {
        return this.jpaRepository.findAll().stream().map(this.assembler::toDomain).toList();
    }

    @Override
    public boolean existsByPolicyNumber(String policyNumber) {
        return this.jpaRepository.existsByPolicyNumber(policyNumber);
    }

    @Override
    public long count() {
        return this.jpaRepository.count();
    }
}
