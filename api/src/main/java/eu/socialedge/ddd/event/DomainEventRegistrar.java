package eu.socialedge.ddd.event;

/**
 * Manages registrations of event handlers
 */
public interface DomainEventRegistrar {

    <T extends DomainEvent> void registerEventHandler(DomainEventHandler<T> eventHandler,
                                                      Class<T> eventType);

    void deregisterEventHandler(Class<? extends DomainEventHandler> eventHandlerClass);
}
