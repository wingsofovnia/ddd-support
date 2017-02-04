package eu.socialedge.ddd.domain.repository;

import eu.socialedge.ddd.domain.DeactivatableAggregateRoot;
import eu.socialedge.ddd.domain.Identifier;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents a mutable {@link Repository} implementation that introduces
 * mechanism of {@link eu.socialedge.ddd.domain.AggregateRoot} deactivation
 * instead of {@code #remove(*)} methods.
 *
 * @param <ID> concrete {@link Identifier} type implementation
 * @param <T> {@link DeactivatableAggregateRoot} type implementation
 */
public interface MindfulRepository<ID extends Identifier<?>, T extends DeactivatableAggregateRoot<ID>>
        extends ExpandableRepository<ID, T> {

    boolean containsActive(ID id);

    void activate(ID id);

    void activate(Iterable<ID> entityIds);

    void deactivate(ID id);

    void deactivate(Iterable<ID> entityIds);

    Optional<T> getActive(ID id);

    Collection<T> listActive();
}
