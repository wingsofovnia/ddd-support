package eu.socialedge.ddd.domain.id;

import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * A default implementation of {@link GeneratableIdentifier} with UUID used
 * as internal value.
 */
@NoArgsConstructor
public abstract class UUIDIdentifier extends GeneratableIdentifier<UUID> {

    public UUIDIdentifier(UUID value) {
        super(value);
    }

    @Override
    protected UUID generate() {
        return UUID.randomUUID();
    }
}
