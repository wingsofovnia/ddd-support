package eu.socialedge.ddd.domain.repository;

/**
 * Represents exceptions in {@link Repository} layer
 */
public class RepositoryException extends RuntimeException {

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    public RepositoryException(String message) {
        super(message);
    }
}
