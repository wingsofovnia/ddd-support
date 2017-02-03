package eu.socialedge.ddd.infrastructure.eventbus;

import eu.socialedge.ddd.event.DomainEvent;
import eu.socialedge.ddd.event.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

@EnableBinding(DomainEventSource.class)
public class SpringDomainEventPublisher implements DomainEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(SpringDomainEventPublisher.class);

    @Autowired
    @Qualifier(DomainEventSource.CHANNEL_NAME)
    private final MessageChannel eventBus;

    @Autowired
    public SpringDomainEventPublisher(MessageChannel eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public <T extends DomainEvent> void publish(T event) {
        logger.debug("Publishing event {} ", event);
        eventBus.send(MessageBuilder.withPayload(event).build());
    }
}
