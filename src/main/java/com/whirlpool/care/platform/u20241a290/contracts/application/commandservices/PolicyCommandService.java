package com.whirlpool.care.platform.u20241a290.contracts.application.commandservices;

import com.whirlpool.care.platform.u20241a290.contracts.domain.model.commands.ProcessClaimCreatedCommand;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.commands.SeedPoliciesCommand;

/**
 * Command service for the Policy aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
public interface PolicyCommandService {
    void handle(SeedPoliciesCommand command);

    void handle(ProcessClaimCreatedCommand command);
}
