package com.whirlpool.care.platform.u20241a290.entitlement.application.commandservices;

import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.commands.CreateClaimCommand;
import com.whirlpool.care.platform.u20241a290.shared.application.result.ApplicationError;
import com.whirlpool.care.platform.u20241a290.shared.application.result.Result;

/**
 * Command service for the Claim aggregate.
 *
 * @author Jose Fernando Flores Pinchi
 */
public interface ClaimCommandService {
    Result<Long, ApplicationError> handle(CreateClaimCommand command);
}
