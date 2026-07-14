package com.whirlpool.care.platform.u20241a290.entitlement.infrastructure.persistence.jpa.entities;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.valueobjects.ClaimStatus;
import com.whirlpool.care.platform.u20241a290.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * JPA persistence entity for the Claim aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Entity
@Table(name = "claims")
@Getter
@Setter
public class ClaimPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "policy_id", nullable = false)
    private Long policyId;

    @Column(name = "issue_description", nullable = false, length = 1000)
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_status", nullable = false, length = 20)
    private ClaimStatus claimStatus;

    @Column(name = "reported_at", nullable = false)
    private LocalDateTime reportedAt;
}
