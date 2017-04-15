package eu.socialedge.ddd.domain.id;

import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * A default implementation of {@link Identifier} with UUID
 * used as internal default value.
 */
@MappedSuperclass
public class UUIDIdentifier extends Identifier<UUID> {

    public UUIDIdentifier() {
        super(UUID.randomUUID());
    }

    public UUIDIdentifier(UUID value) {
        super(value);
    }
}
