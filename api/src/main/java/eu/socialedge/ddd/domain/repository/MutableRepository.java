package eu.socialedge.ddd.domain.repository;

import eu.socialedge.ddd.domain.AggregateRoot;
import eu.socialedge.ddd.domain.Identifier;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Represents a fully mutable {@link Repository} with {@code #add(*)},
 * {@code #remove(*)} and {@code #clear()} methods.
 *
 * @param <ID> concrete {@link Identifier} type implementation
 * @param <T> {@link AggregateRoot} type implementation
 */
public interface MutableRepository<ID extends Identifier<?>, T extends AggregateRoot<ID>>
        extends ExpandableRepository<ID, T> {

    boolean remove(ID id);

    default boolean remove(T entity) {
        return remove(entity.id());
    }

    void remove(Iterable<ID> entityIds);

    default void remove(Collection<T> entities) {
        remove(entities.stream().map(T::id).collect(Collectors.toList()));
    }

    void clear();
}
