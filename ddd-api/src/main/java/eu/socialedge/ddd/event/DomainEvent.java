package eu.socialedge.ddd.event;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Domain Event is a type of message that describes something that
 * has happened in the past and is of interest to the business and event.
 *
 * @see <a href="https://martinfowler.com/eaaDev/DomainEvent.html">
 *     Domain Event - Martin Fowler</a>
 */
public abstract class DomainEvent implements Serializable {

    protected final LocalDateTime creationTime = LocalDateTime.now();

    public LocalDateTime creationTime() {
        return creationTime;
    }
}
