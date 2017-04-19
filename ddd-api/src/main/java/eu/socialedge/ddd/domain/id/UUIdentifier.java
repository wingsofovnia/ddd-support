package eu.socialedge.ddd.domain.id;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * {@link UUID} based {@link Identifier} implementation with default
 * constructor, that creates an instance with random {@link UUID} value inside
 */
@MappedSuperclass
public class UUIdentifier extends Identifier<UUID> {

    public UUIdentifier() {
        super(UUID.randomUUID());
    }

    public UUIdentifier(UUID value) {
        super(value);
    }
}
