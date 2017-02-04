package eu.socialedge.ddd.domain;

import java.io.Serializable;

/**
 * Represents a concept from the event that is considered a value.
 *
 * <p>In terms of classes that means that individual instances don’t
 * have identity, no lifecycle. A value object needs to be immutable
 * to ensure integrity of the instances as they can be shared amongst
 * different consumers.</p>
 *
 * <p> Value objects are usually part of other model elements like
 * {@link Entity} or {@link AggregateRoot}.</p>
 *
 * @see <a href="https://goo.gl/4UpUV8">
 *     Domain-Driven Design and Spring - Oliver Gierke</a>
 */
public abstract class ValueObject implements Serializable {
}
