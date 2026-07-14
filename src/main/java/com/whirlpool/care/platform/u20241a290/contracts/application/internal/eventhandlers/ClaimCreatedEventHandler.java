package com.whirlpool.care.platform.u20241a290.contracts.application.internal.eventhandlers;

import com.whirlpool.care.platform.u20241a290.contracts.application.commandservices.PolicyCommandService;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.commands.ProcessClaimCreatedCommand;
import com.whirlpool.care.platform.u20241a290.shared.domain.model.events.ClaimCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Contracts bounded context handler for the ClaimCreatedEvent. Delegates to the Policy command
 * service, which applies idempotency, a single transaction and optimistic locking while updating
 * the policy lastClaimId.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Component
public class ClaimCreatedEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimCreatedEventHandler.class);
    private final PolicyCommandService policyCommandService;

    public ClaimCreatedEventHandler(PolicyCommandService policyCommandService) {
        this.policyCommandService = policyCommandService;
    }

    @EventListener
    public void on(ClaimCreatedEvent event) {
        LOGGER.info("Handling ClaimCreatedEvent for policy {} and claim {}", event.policyId(), event.claimId());
        this.policyCommandService.handle(new ProcessClaimCreatedCommand(event.policyId(), event.claimId()));
    }
}
