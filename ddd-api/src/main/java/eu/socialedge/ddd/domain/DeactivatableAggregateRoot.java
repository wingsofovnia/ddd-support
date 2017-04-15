package eu.socialedge.ddd.domain;

import eu.socialedge.ddd.domain.id.Identifier;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter @Accessors(fluent = true)
@MappedSuperclass @Access(AccessType.FIELD)
public abstract class DeactivatableAggregateRoot<T extends Identifier<?>> extends AggregateRoot<T> {

    @Column(name = "is_active", nullable = false)
    protected boolean isActive = true;

    protected DeactivatableAggregateRoot() {}

    protected DeactivatableAggregateRoot(T id) {
        super(id);
    }

    protected DeactivatableAggregateRoot(boolean isActive) {
        this.isActive = isActive;
    }

    protected DeactivatableAggregateRoot(T id, boolean isActive) {
        super(id);
        this.isActive = isActive;
    }

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }
}
