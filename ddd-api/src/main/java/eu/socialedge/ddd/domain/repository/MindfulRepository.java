package eu.socialedge.ddd.domain.repository;

import eu.socialedge.ddd.domain.DeactivatableAggregateRoot;
import eu.socialedge.ddd.domain.Identifier;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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

    void activate(ID id);

    default void activate(T entity) {
        activate(entity.id());
    }

    void activate(Iterable<ID> entityIds);

    default void activate(Collection<T> entities) {
        activate(entities.stream().map(T::id).collect(Collectors.toList()));
    }

    void deactivate(ID id);

    default void deactivate(T entity) {
        deactivate(entity.id());
    }

    void deactivate(Iterable<ID> entityIds);

    default void deactivate(Collection<T> entities) {
        deactivate(entities.stream().map(T::id).collect(Collectors.toList()));
    }

    Optional<T> getActive(ID id);

    Collection<T> listActive();
}
