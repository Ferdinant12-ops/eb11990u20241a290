package com.whirlpool.care.platform.u20241a290.contracts.domain.model.aggregates;

import com.whirlpool.care.platform.u20241a290.contracts.domain.model.valueobjects.CoverageStatus;
import com.whirlpool.care.platform.u20241a290.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.whirlpool.care.platform.u20241a290.shared.domain.model.valueobjects.Period;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Aggregate root for the contracts bounded context. Represents an extended-care policy.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Getter
@Setter
public class Policy extends AbstractDomainAggregateRoot<Policy> {

    private Long id;
    private String policyNumber;
    private String applianceModel;
    private Period coveragePeriod;
    private Long lastClaimId;

    /**
     * Default constructor required by frameworks.
     */
    public Policy() {
    }

    /**
     * Creates a new policy with an active (unassigned) claim slot.
     *
     * @param policyNumber   the unique policy number
     * @param applianceModel the covered appliance model
     * @param coveragePeriod the coverage period value object
     */
    public Policy(String policyNumber, String applianceModel, Period coveragePeriod) {
        this.policyNumber = policyNumber;
        this.applianceModel = applianceModel;
        this.coveragePeriod = coveragePeriod;
        this.lastClaimId = null;
    }

    /**
     * Calculates the coverage status as a domain invariant (not persisted).
     *
     * @return ACTIVE if the current date is within the coverage period, EXPIRED otherwise
     */
    public CoverageStatus coverageStatus() {
        return this.coveragePeriod.contains(LocalDate.now()) ? CoverageStatus.ACTIVE : CoverageStatus.EXPIRED;
    }

    /**
     * Indicates whether this policy can accept a new claim. While a lastClaimId is present,
     * the policy is considered IN_PROGRESS and not eligible.
     *
     * @return true only when no claim is currently in progress
     */
    public boolean isEligibleForClaim() {
        return this.lastClaimId == null;
    }

    /**
     * Assigns the identifier of the last processed claim (operational tracking).
     *
     * @param claimId the processed claim identifier
     */
    public void assignLastClaim(Long claimId) {
        this.lastClaimId = claimId;
    }
}
