package eu.socialedge.ddd.event;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Domain Event is a type of message that describes something that
 * has happened in the past and is of interest to the business and event.
 *
 * @see <a href="https://martinfowler.com/eaaDev/DomainEvent.html">
 *     Domain Event - Martin Fowler</a>
 */
@Getter @Accessors(fluent = true)
public abstract class DomainEvent implements Serializable {

    protected final LocalDateTime creationTime = LocalDateTime.now();
}
