package com.whirlpool.care.platform.u20241a290.shared.domain.model.valueobjects;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Value Object representing a coverage period bounded by an inclusive start and end date.
 * Located in the shared bounded context because it is reused across contexts.
 *
 * @param startDate the inclusive start date
 * @param endDate   the inclusive end date
 * @author Jose Fernando Flores Pinchi
 */
public record Period(LocalDate startDate, LocalDate endDate) {

    public Period {
        Objects.requireNonNull(startDate, "[Period] startDate cannot be null");
        Objects.requireNonNull(endDate, "[Period] endDate cannot be null");
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("[Period] endDate cannot be before startDate");
        }
    }

    /**
     * Checks whether the given date falls within this period (inclusive).
     *
     * @param date the date to test
     * @return true if date is within [startDate, endDate]
     */
    public boolean contains(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
