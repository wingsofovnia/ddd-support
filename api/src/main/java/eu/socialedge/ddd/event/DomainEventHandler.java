package eu.socialedge.ddd.event;

/**
 * {@code DomainEventHandler}s are responsible for handling
 * {@link DomainEvent}s and its child implementations.
 *
 * @param <T> event type
 */
public interface DomainEventHandler<T extends DomainEvent> {

    <E extends T> void handleEvent(E domainEvent);
}
