package com.whirlpool.care.platform.u20241a290.entitlement.domain.model.commands;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.valueobjects.ClaimStatus;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Command carrying the data required to create a Claim.
 *
 * @author Jose Fernando Flores Pinchi
 */
public record CreateClaimCommand(Long policyId, String issueDescription, ClaimStatus claimStatus,
                                 LocalDateTime reportedAt) {

    public CreateClaimCommand {
        Objects.requireNonNull(policyId, "[CreateClaimCommand] policyId cannot be null");
        Objects.requireNonNull(claimStatus, "[CreateClaimCommand] claimStatus cannot be null");
        Objects.requireNonNull(reportedAt, "[CreateClaimCommand] reportedAt cannot be null");
        if (issueDescription == null || issueDescription.isBlank()) {
            throw new IllegalArgumentException("[CreateClaimCommand] issueDescription cannot be null or blank");
        }
        if (reportedAt.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("[CreateClaimCommand] reportedAt cannot be in the future");
        }
    }
}
