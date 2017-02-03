package eu.socialedge.ddd.infrastructure.eventbus;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DomainEventSource {

    String CHANNEL_NAME = "eventBus";

    @Output(DomainEventSource.CHANNEL_NAME)
    MessageChannel eventBus();

}
