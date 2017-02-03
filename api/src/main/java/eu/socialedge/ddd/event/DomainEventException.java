package eu.socialedge.ddd.event;

/**
 * Represents exceptions in eventbus layer
 */
public class DomainEventException extends RuntimeException {

    public DomainEventException(String message) {
        super(message);
    }

    public DomainEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainEventException(Throwable cause) {
        super(cause);
    }
}
