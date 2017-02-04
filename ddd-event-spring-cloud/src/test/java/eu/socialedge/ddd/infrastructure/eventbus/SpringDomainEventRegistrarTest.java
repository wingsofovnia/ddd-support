package eu.socialedge.ddd.infrastructure.eventbus;

import eu.socialedge.ddd.event.DomainEvent;
import eu.socialedge.ddd.event.DomainEventHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpringDomainEventRegistrarTest {

    private @Mock
    DomainEventHandler<DomainEvent> firstHandler;
    private @Mock DomainEvent firstEvent;

    private static class ChildDomainEvent extends DomainEvent {}
    private @Mock ChildDomainEvent childDomainEvent;

    private static abstract class ChildDomainEventHandler implements DomainEventHandler<ChildDomainEvent> {}
    private @Mock ChildDomainEventHandler childDomainEventHandler;

    private SpringDomainEventRegistrar eventRegistrar = new SpringDomainEventRegistrar();

    @Test
    public void shouldCallHandlerForHisEvent() {
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);

        eventRegistrar.handleEvent(firstEvent);

        verify(firstHandler).handleEvent(firstEvent);
        verifyZeroInteractions(childDomainEventHandler);
    }

    @Test
    public void shouldNotHandleChildDomainEvent() {
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);
        eventRegistrar.registerEventHandler(childDomainEventHandler, ChildDomainEvent.class);

        eventRegistrar.handleEvent(firstEvent);
        verifyZeroInteractions(childDomainEventHandler);
    }

    @Test
    public void shouldHandleChildEventsAssignableFromParent() {
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);
        eventRegistrar.registerEventHandler(childDomainEventHandler, ChildDomainEvent.class);

        eventRegistrar.handleEvent(childDomainEvent);

        verify(childDomainEventHandler).handleEvent(childDomainEvent);
        verify(firstHandler).handleEvent(childDomainEvent);
    }

    @Test
    public void shouldCallAllHandlersForTheirEvent() {
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);

        eventRegistrar.handleEvent(firstEvent);

        verify(firstHandler, times(2)).handleEvent(firstEvent);
        verifyZeroInteractions(childDomainEventHandler);
    }

    @Test
    public void shouldCallAllHandlersDespiteExceptions() {
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);

        doThrow(new RuntimeException("Something wrong happened in handler")).when(firstHandler).handleEvent(firstEvent);

        eventRegistrar.handleEvent(firstEvent);

        verify(firstHandler, times(2)).handleEvent(firstEvent);
        verifyZeroInteractions(childDomainEventHandler);
    }

    @Test
    public void shouldDeregisterOnlySpecifiedHandlers() {
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);
        eventRegistrar.registerEventHandler(childDomainEventHandler, ChildDomainEvent.class);

        eventRegistrar.deregisterEventHandler(firstHandler.getClass());

        eventRegistrar.handleEvent(childDomainEvent);

        verify(childDomainEventHandler).handleEvent(childDomainEvent);
        verifyZeroInteractions(firstHandler);
    }


    @Test
    public void shouldNotCallDeregisteredHandlers() {
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);
        eventRegistrar.registerEventHandler(childDomainEventHandler, ChildDomainEvent.class);

        eventRegistrar.deregisterEventHandler(firstHandler.getClass());

        eventRegistrar.handleEvent(firstEvent);

        verifyZeroInteractions(firstHandler, childDomainEventHandler);
    }

    @Test
    public void shouldDeregisterAllSuitableHandlers() {
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);
        eventRegistrar.registerEventHandler(firstHandler, DomainEvent.class);

        eventRegistrar.deregisterEventHandler(firstHandler.getClass());

        eventRegistrar.handleEvent(firstEvent);

        verifyZeroInteractions(firstHandler, childDomainEventHandler);
    }

}
