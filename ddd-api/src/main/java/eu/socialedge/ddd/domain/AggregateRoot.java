package eu.socialedge.ddd.domain;

import lombok.NoArgsConstructor;

/**
 * Represents a DDD Aggregate Root - a cluster of event objects
 * that can be treated as a single unit.
 *
 * <p>An aggregate will have one of its component objects be the
 * aggregate root. Any references from outside the aggregate should
 * only go to the aggregate root. The root can thus ensure the integrity
 * of the aggregate as a whole.</p>
 *
 * @see <a href="https://martinfowler.com/bliki/DDD_Aggregate.html">
 *     DDD_Aggregate - Martin Fowler</a>
 */
@NoArgsConstructor(force = true)
public abstract class AggregateRoot<T extends Identifier<?>> extends Entity<T> {

    protected AggregateRoot(T id) {
        super(id);
    }
}
