package com.whirlpool.care.platform.u20241a290.entitlement.infrastructure.persistence.jpa.adapters;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates.Claim;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.repositories.ClaimRepository;
import com.whirlpool.care.platform.u20241a290.entitlement.infrastructure.persistence.jpa.assemblers.ClaimPersistenceAssembler;
import com.whirlpool.care.platform.u20241a290.entitlement.infrastructure.persistence.jpa.repositories.ClaimPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository adapter implementing the domain ClaimRepository over Spring Data JPA.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Repository
public class ClaimRepositoryImpl implements ClaimRepository {

    private final ClaimPersistenceRepository jpaRepository;
    private final ClaimPersistenceAssembler assembler;

    public ClaimRepositoryImpl(ClaimPersistenceRepository jpaRepository, ClaimPersistenceAssembler assembler) {
        this.jpaRepository = jpaRepository;
        this.assembler = assembler;
    }

    @Override
    public Claim save(Claim claim) {
        var saved = this.jpaRepository.save(this.assembler.toNewEntity(claim));
        return this.assembler.toDomain(saved);
    }

    @Override
    public Optional<Claim> findById(Long id) {
        return this.jpaRepository.findById(id).map(this.assembler::toDomain);
    }
}
