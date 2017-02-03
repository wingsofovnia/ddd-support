package eu.socialedge.ddd.event;

/**
 * Emits {@link DomainEvent}s
 */
public interface DomainEventPublisher {

    <T extends DomainEvent> void publish(T event);
}
