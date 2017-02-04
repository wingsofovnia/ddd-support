package eu.socialedge.ddd.domain.repository;

import eu.socialedge.ddd.domain.AggregateRoot;
import eu.socialedge.ddd.domain.Identifier;

import java.util.Collection;
import java.util.Optional;

/**
 * Conceptually a repository simulates a collection of aggregate
 * roots and allows accessing subsets or individual items.
 *
 * <p>They’re usually backed by some kind of persistence mechanism
 * but shouldn’t expose it to client code. Repositories refer to
 * entities, not the other way round.</p>
 *
 * @see <a href="https://goo.gl/mW15Nn">
 *     Domain-Driven Design and Spring - Oliver Gierke</a>
 *
 * @param <ID> concrete {@link Identifier} type implementation
 * @param <T> {@link AggregateRoot} type implementation
 */
public interface Repository<ID extends Identifier<?>, T extends AggregateRoot<ID>> {

    boolean contains(ID id);

    long size();

    default boolean isEmpty() {
        return size() == 0L;
    }

    Optional<T> get(ID id);

    Collection<T> list();
}
