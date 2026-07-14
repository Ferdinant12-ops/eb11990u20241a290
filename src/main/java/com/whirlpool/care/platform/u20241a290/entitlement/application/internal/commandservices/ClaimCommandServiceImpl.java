package com.whirlpool.care.platform.u20241a290.entitlement.application.internal.commandservices;

import com.whirlpool.care.platform.u20241a290.entitlement.application.commandservices.ClaimCommandService;
import com.whirlpool.care.platform.u20241a290.entitlement.application.internal.outboundservices.acl.ExternalPolicyService;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates.Claim;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.commands.CreateClaimCommand;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.repositories.ClaimRepository;
import com.whirlpool.care.platform.u20241a290.shared.application.result.ApplicationError;
import com.whirlpool.care.platform.u20241a290.shared.application.result.Result;
import com.whirlpool.care.platform.u20241a290.shared.domain.model.events.ClaimCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Command service implementation for creating Claims. Validates the associated policy through the
 * ACL (existence and non-expired coverage) and emits a ClaimCreatedEvent on success.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Service
public class ClaimCommandServiceImpl implements ClaimCommandService {

    private final ClaimRepository claimRepository;
    private final ExternalPolicyService externalPolicyService;
    private final ApplicationEventPublisher eventPublisher;

    public ClaimCommandServiceImpl(ClaimRepository claimRepository,
                                   ExternalPolicyService externalPolicyService,
                                   ApplicationEventPublisher eventPublisher) {
        this.claimRepository = claimRepository;
        this.externalPolicyService = externalPolicyService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateClaimCommand command) {
        if (!this.externalPolicyService.existsPolicy(command.policyId())) {
            return Result.failure(ApplicationError.notFound("policy", String.valueOf(command.policyId())));
        }
        if (this.externalPolicyService.isCoverageExpired(command.policyId())) {
            return Result.failure(ApplicationError.businessRuleViolation(
                    "expired-coverage",
                    "The coverage for policy %d is EXPIRED and cannot accept new claims".formatted(command.policyId())));
        }
        Claim claim;
        try {
            claim = this.claimRepository.save(new Claim(command));
        } catch (Exception ex) {
            return Result.failure(ApplicationError.unexpected("claim", ex.getMessage()));
        }
        this.eventPublisher.publishEvent(new ClaimCreatedEvent(claim.getPolicyId(), claim.getId(), claim.getReportedAt()));
        return Result.success(claim.getId());
    }
}
