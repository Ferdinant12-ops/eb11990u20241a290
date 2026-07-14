package com.whirlpool.care.platform.u20241a290.shared.domain.model.events;

import java.time.LocalDateTime;

/**
 * Integration event emitted when a Claim is created in the entitlement bounded context,
 * and consumed by the contracts bounded context to update the associated Policy.
 *
 * @param policyId   the identifier of the associated policy
 * @param claimId    the identifier of the created claim
 * @param reportedAt the moment the claim was reported
 * @author Jose Fernando Flores Pinchi
 */
public record ClaimCreatedEvent(Long policyId, Long claimId, LocalDateTime reportedAt) {
}
