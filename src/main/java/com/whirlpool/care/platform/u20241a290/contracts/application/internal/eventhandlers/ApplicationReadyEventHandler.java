package com.whirlpool.care.platform.u20241a290.contracts.application.internal.eventhandlers;

import com.whirlpool.care.platform.u20241a290.contracts.application.commandservices.PolicyCommandService;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.commands.SeedPoliciesCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Seeds the initial Policies data automatically once the application is ready.
 *
 * @author Jose Fernando Flores Pinchi
 */
@Service
public class ApplicationReadyEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);
    private final PolicyCommandService policyCommandService;

    public ApplicationReadyEventHandler(PolicyCommandService policyCommandService) {
        this.policyCommandService = policyCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Verifying policies seeding for {}", applicationName);
        this.policyCommandService.handle(new SeedPoliciesCommand());
        LOGGER.info("Policies seeding verification finished for {}", applicationName);
    }
}
