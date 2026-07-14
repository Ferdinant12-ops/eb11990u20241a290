package com.whirlpool.care.platform.u20241a290.contracts.infrastructure.persistence.jpa.entities;

import com.whirlpool.care.platform.u20241a290.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * JPA persistence entity for the Policy aggregate. Uses an optimistic-locking version column.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Entity
@Table(name = "policies")
@Getter
@Setter
public class PolicyPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Version
    @Column(nullable = false)
    private Long version;

    @Column(name = "policy_number", nullable = false, unique = true, length = 20)
    private String policyNumber;

    @Column(name = "appliance_model", nullable = false)
    private String applianceModel;

    @Column(name = "coverage_start_date", nullable = false)
    private LocalDate coverageStartDate;

    @Column(name = "coverage_end_date", nullable = false)
    private LocalDate coverageEndDate;

    @Column(name = "last_claim_id")
    private Long lastClaimId;
}
