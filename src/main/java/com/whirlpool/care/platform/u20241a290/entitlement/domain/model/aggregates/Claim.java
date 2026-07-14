package com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.commands.CreateClaimCommand;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.valueobjects.ClaimStatus;
import com.whirlpool.care.platform.u20241a290.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Aggregate root for the entitlement bounded context. Represents a support claim.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Getter
@Setter
public class Claim extends AbstractDomainAggregateRoot<Claim> {

    private Long id;
    private Long policyId;
    private String issueDescription;
    private ClaimStatus claimStatus;
    private LocalDateTime reportedAt;

    public Claim() {
    }

    public Claim(CreateClaimCommand command) {
        this.policyId = command.policyId();
        this.issueDescription = command.issueDescription();
        this.claimStatus = command.claimStatus();
        this.reportedAt = command.reportedAt();
    }
}
