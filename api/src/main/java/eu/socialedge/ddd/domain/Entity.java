package eu.socialedge.ddd.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import static java.util.Objects.requireNonNull;

/**
 * A {@link ValueObject} fundamentally defined not by its
 * attributes, but by a thread of continuity and identity.
 *
 * <p>In comparison to value objects, an entity’s core
 * trait is it’s identity. Two customers named Michael
 * Müller might not constitue the very same instance, so
 * that usually a dedicated property is introduced to capture
 * the identity. Another core trait of entities is that they’re
 * usually subject to a certain lifecycle within the problem
 * event. They get created, they undergo certain state changes
 * usually driven by event events and might reach an end state
 * (i.e. might be deleted, although this doesn’t necessary mean
 * that the information is removed from the system).</p>
 *
 * <p>Entities usually relate to other entities and contain
 * properties that are value objects or primitives (the former
 * preferred).</p>
 *
 * @see <a href="https://goo.gl/4UpUV8">
 *     Domain-Driven Design and Spring - Oliver Gierke</a>
 *
 * @param <T> concrete {@link Identifier} type implementation
 */
@Getter @Setter
@EqualsAndHashCode
@Accessors(fluent = true)
@NoArgsConstructor(force = true, access = AccessLevel.PACKAGE)
@MappedSuperclass @Access(AccessType.FIELD)
public abstract class Entity<T extends Identifier<?>> {

    @EmbeddedId
    protected final T id;

    protected Entity(T id) {
        this.id = requireNonNull(id);
    }
}
