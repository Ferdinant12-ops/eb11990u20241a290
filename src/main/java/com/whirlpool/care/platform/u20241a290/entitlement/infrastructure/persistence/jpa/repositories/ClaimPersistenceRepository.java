package com.whirlpool.care.platform.u20241a290.entitlement.infrastructure.persistence.jpa.repositories;

import com.whirlpool.care.platform.u20241a290.entitlement.infrastructure.persistence.jpa.entities.ClaimPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for ClaimPersistenceEntity.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Repository
public interface ClaimPersistenceRepository extends JpaRepository<ClaimPersistenceEntity, Long> {
}
