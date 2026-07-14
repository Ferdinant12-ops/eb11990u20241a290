package com.whirlpool.care.platform.u20241a290.shared.domain.model.aggregates;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Collection;

/**
 * Base class for all domain aggregate roots. Provides domain-event registration support.
 *
 * @param <T> the concrete aggregate root type
 * @author Jose Fernando Flores Pinchi
 */
@NullMarked
public abstract class AbstractDomainAggregateRoot<T extends AbstractDomainAggregateRoot<T>>
        extends AbstractAggregateRoot<T> {

    protected void registerDomainEvent(Object event) {
        super.registerEvent(event);
    }

    @Override
    public Collection<Object> domainEvents() {
        return super.domainEvents();
    }

    @Override
    public void clearDomainEvents() {
        super.clearDomainEvents();
    }
}
