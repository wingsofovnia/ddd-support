package eu.socialedge.ddd.infrastructure.eventbus;

import eu.socialedge.ddd.event.DomainEvent;
import eu.socialedge.ddd.event.DomainEventHandler;
import eu.socialedge.ddd.event.DomainEventPublisher;
import eu.socialedge.ddd.event.DomainEventRegistrar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootTest(properties = {"server.port=-1"})
@DirtiesContext
public class SpringDomainEventRegistrarIntegrationTest {

    @Autowired
    private DomainEventPublisher springDomainEventPublisher;

    @Autowired
    private DomainEventRegistrar springDomainEventRegistrar;

    private @Mock DomainEventHandler<DomainEvent> handler;
    private @Mock DomainEventHandler<DomainEvent> anotherHandler;

    private @Mock DomainEvent event;
    private @Mock DomainEvent anotherEvent;

    @Test
    public void shouldHandlePublishedEvent() {
        springDomainEventRegistrar.registerEventHandler(handler, DomainEvent.class);

        springDomainEventPublisher.publish(event);

        for (int i = 0; i < 1000; i++) {}

        verify(handler).handleEvent(event);
    }

    @Test
    public void shouldHandleEventEveryTimeItIsPublished() {
        springDomainEventRegistrar.registerEventHandler(handler, DomainEvent.class);

        springDomainEventPublisher.publish(event);
        springDomainEventPublisher.publish(event);

        verify(handler, times(2)).handleEvent(event);
    }

    @Test
    public void shouldHandleAllPublishedEvents() {
        springDomainEventRegistrar.registerEventHandler(handler, DomainEvent.class);

        springDomainEventPublisher.publish(event);
        springDomainEventPublisher.publish(anotherEvent);

        verify(handler).handleEvent(event);
        verify(handler).handleEvent(anotherEvent);
    }

    @Test
    public void shouldHandleAllPublishedEventsByAllEligibleHandlers() {
        springDomainEventRegistrar.registerEventHandler(handler, DomainEvent.class);
        springDomainEventRegistrar.registerEventHandler(anotherHandler, DomainEvent.class);

        springDomainEventPublisher.publish(event);
        springDomainEventPublisher.publish(anotherEvent);

        verify(handler).handleEvent(event);
        verify(handler).handleEvent(anotherEvent);
        verify(anotherHandler).handleEvent(event);
        verify(anotherHandler).handleEvent(anotherEvent);
    }
}
