package com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest;

import com.whirlpool.care.platform.u20241a290.entitlement.application.commandservices.ClaimCommandService;
import com.whirlpool.care.platform.u20241a290.entitlement.application.queryservices.ClaimQueryService;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.aggregates.Claim;
import com.whirlpool.care.platform.u20241a290.entitlement.domain.model.queries.GetClaimByIdQuery;
import com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.resources.ClaimResource;
import com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.resources.CreateClaimResource;
import com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.transform.ClaimResourceFromEntityAssembler;
import com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.transform.CreateClaimCommandFromResourceAssembler;
import com.whirlpool.care.platform.u20241a290.shared.application.result.ApplicationError;
import com.whirlpool.care.platform.u20241a290.shared.application.result.Result;
import com.whirlpool.care.platform.u20241a290.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing the claim registration operation.
 *
 * @author Jose Fernando Flores Pinchi
 */
@RestController
@RequestMapping(value = "/api/v1/claims", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Claims", description = "Claim registration endpoints")
public class ClaimsController {

    private final ClaimCommandService claimCommandService;
    private final ClaimQueryService claimQueryService;

    public ClaimsController(ClaimCommandService claimCommandService, ClaimQueryService claimQueryService) {
        this.claimCommandService = claimCommandService;
        this.claimQueryService = claimQueryService;
    }

    @PostMapping
    @Operation(summary = "Register a new claim",
            description = "Registers a claim after validating the policy exists and its coverage is not expired.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Claim created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Associated policy not found"),
            @ApiResponse(responseCode = "422", description = "Coverage expired - claim rejected")
    })
    public ResponseEntity<?> createClaim(@RequestBody CreateClaimResource resource) {
        var command = CreateClaimCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = this.claimCommandService.handle(command)
                .flatMap(claimId -> this.claimQueryService.handle(new GetClaimByIdQuery(claimId))
                        .<Result<Claim, ApplicationError>>map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("claim", String.valueOf(claimId)))));
        return ResponseEntityAssembler.toResponseEntityFromResult(result,
                ClaimResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED);
    }
}
