package com.whirlpool.care.platform.u20241a290.entitlement.domain.model.valueobjects;

/**
 * Enumeration of the possible statuses of a Claim.
 *
 * @author Jose Fernando Flores Pinchi
 */
public enum ClaimStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    DENIED;

    /**
     * Resolves a ClaimStatus from its String representation.
     *
     * @param value the status name
     * @return the matching ClaimStatus
     */
    public static ClaimStatus fromString(String value) {
        try {
            return ClaimStatus.valueOf(value.toUpperCase());
        } catch (Exception ex) {
            throw new IllegalArgumentException("[ClaimStatus] Invalid claimStatus value: " + value);
        }
    }
}
