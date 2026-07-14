package com.whirlpool.care.platform.u20241a290.contracts.infrastructure.persistence.jpa.repositories;

import com.whirlpool.care.platform.u20241a290.contracts.infrastructure.persistence.jpa.entities.PolicyPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for PolicyPersistenceEntity.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Repository
public interface PolicyPersistenceRepository extends JpaRepository<PolicyPersistenceEntity, Long> {
    boolean existsByPolicyNumber(String policyNumber);
}
