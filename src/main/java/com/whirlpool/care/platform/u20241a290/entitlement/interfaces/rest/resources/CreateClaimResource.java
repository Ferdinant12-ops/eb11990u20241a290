package com.whirlpool.care.platform.u20241a290.entitlement.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * REST request resource for creating a Claim. The id is auto-generated and not provided.
 *
 * @author Jose Fernando Flores Pinchi
 */
public record CreateClaimResource(

        @Schema(description = "Identifier of the associated policy", example = "1")
        Long policyId,

        @Schema(description = "Description of the reported issue", example = "Compressor not cooling")
        String issueDescription,

        @Schema(description = "Claim status (OPEN, IN_PROGRESS, RESOLVED, DENIED)", example = "OPEN")
        String claimStatus,

        @Schema(description = "Report date-time", example = "2026-07-13 20:13:32", format = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime reportedAt) {
}
