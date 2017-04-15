package eu.socialedge.ddd.domain;

import eu.socialedge.ddd.domain.id.Identifier;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * {@link UUID} based implementation of {@link Identifier}
 */
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass @Access(AccessType.FIELD)
public class UuId extends Identifier<UUID> {

    public UuId() {
        super(UUID.randomUUID());
    }

    public UuId(UUID uuid) {
        super(uuid);
    }
}
