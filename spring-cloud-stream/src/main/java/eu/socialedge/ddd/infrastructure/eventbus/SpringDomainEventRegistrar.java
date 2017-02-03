package eu.socialedge.ddd.infrastructure.eventbus;

import eu.socialedge.ddd.event.DomainEvent;
import eu.socialedge.ddd.event.DomainEventHandler;
import eu.socialedge.ddd.event.DomainEventRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableBinding(DomainEventSink.class)
public class SpringDomainEventRegistrar implements DomainEventRegistrar {

    private static final Logger logger = LoggerFactory.getLogger(SpringDomainEventRegistrar.class);

    private final Map<Class<? extends DomainEvent>, List<DomainEventHandler<?>>> handlers = new HashMap<>();

    @Override
    public <T extends DomainEvent> void registerEventHandler(DomainEventHandler<T> eventHandler, Class<T> eventType) {
        logger.debug("Registering event handler '{}' for event type '{}'", eventHandler, eventType);

        handlers.computeIfAbsent(eventType, k -> new ArrayList<>())
                .add(eventHandler);
    }

    @Override
    public void deregisterEventHandler(Class<? extends DomainEventHandler> eventHandlerClass) {
        logger.debug("Deregistering event handler '{}'", eventHandlerClass);

        handlers.values().forEach(eventHandlers -> {
            eventHandlers.removeIf(handler -> handler.getClass().equals(eventHandlerClass));
        });
    }

    @SuppressWarnings("unchecked")
    @StreamListener(DomainEventSink.CHANNEL_NAME)
    <T extends DomainEvent> void handleEvent(T event) {
        logger.debug("Received event '{}' for handling", event);

        handlers.keySet().stream()
                .filter(eventType -> eventType.isAssignableFrom(event.getClass()))
                .flatMap(handledEvent -> handlers.get(handledEvent).stream())
                .forEach(eventHandler -> {
                    try {
                        logger.debug("Delegating event '{}' to handler '{}'", event, eventHandler);
                        ((DomainEventHandler<T>) eventHandler).handleEvent(event);
                    } catch (Exception ex) {
                        //Ignored to let other handlers process event
                        logger.error("Handler '{}' threw exception for event '{}'", eventHandler, event, ex);
                    }
                });
    }
}
