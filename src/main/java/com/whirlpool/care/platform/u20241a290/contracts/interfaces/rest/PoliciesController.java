package com.whirlpool.care.platform.u20241a290.contracts.interfaces.rest;

import com.whirlpool.care.platform.u20241a290.contracts.application.queryservices.PolicyQueryService;
import com.whirlpool.care.platform.u20241a290.contracts.domain.model.queries.GetAllPoliciesQuery;
import com.whirlpool.care.platform.u20241a290.contracts.interfaces.rest.resources.PolicyResource;
import com.whirlpool.care.platform.u20241a290.contracts.interfaces.rest.transform.PolicyResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller exposing the policies catalog.
 *
 * @author Jose Fernando Flores Pinchi
 */
@RestController
@RequestMapping(value = "/api/v1/policies", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Policies", description = "Policy catalog endpoints")
public class PoliciesController {

    private final PolicyQueryService policyQueryService;

    public PoliciesController(PolicyQueryService policyQueryService) {
        this.policyQueryService = policyQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all policies", description = "Retrieves the currently stored policies.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Policies retrieved successfully")
    })
    public ResponseEntity<List<PolicyResource>> getAllPolicies() {
        var policies = this.policyQueryService.handle(new GetAllPoliciesQuery());
        var resources = policies.stream()
                .map(PolicyResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
