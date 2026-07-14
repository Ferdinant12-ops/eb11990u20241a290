package com.whirlpool.care.platform.u20241a290.contracts.domain.model.commands;

/**
 * Command to process a created claim against its policy (updates lastClaimId).
 *
 * @param policyId the target policy identifier
 * @param claimId  the created claim identifier
 * @author Jose Fernando Flores Pinchi
 */
public record ProcessClaimCreatedCommand(Long policyId, Long claimId) {
}
