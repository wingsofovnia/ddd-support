package eu.socialedge.ddd.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter @Accessors(fluent = true)
@NoArgsConstructor(force = true, access = AccessLevel.PACKAGE)
@MappedSuperclass @Access(AccessType.FIELD)
public abstract class DeactivatableAggregateRoot<T extends Identifier<?>> extends AggregateRoot<T> {

    @Column(name = "is_active", nullable = false)
    protected boolean isActive = true;

    protected DeactivatableAggregateRoot(T id) {
        super(id);
    }

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }
}
