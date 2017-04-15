package eu.socialedge.ddd.domain.repository;

import eu.socialedge.ddd.domain.AggregateRoot;
import eu.socialedge.ddd.domain.id.Identifier;

import java.util.Collection;

/**
 * A {@link Repository} that can grow in size. Introduces
 * {@link #add(Collection)} and {@link #add(AggregateRoot)}
 * methods.
 *
 * @param <ID> concrete {@link Identifier} type implementation
 * @param <T> {@link AggregateRoot} type implementation
 */
public interface ExpandableRepository <ID extends Identifier<?>, T extends AggregateRoot<ID>>
        extends Repository<ID, T> {

    void add(T entity);

    void add(Collection<T> entities);
}
