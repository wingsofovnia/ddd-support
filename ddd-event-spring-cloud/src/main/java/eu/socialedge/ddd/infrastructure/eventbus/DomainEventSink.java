package eu.socialedge.ddd.infrastructure.eventbus;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface DomainEventSink {

    String CHANNEL_NAME = "eventBus";

    @Input(DomainEventSink.CHANNEL_NAME)
    SubscribableChannel eventBus();

}
