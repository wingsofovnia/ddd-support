package eu.socialedge.ddd.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter @Accessors(fluent = true)
@NoArgsConstructor(force = true)
@MappedSuperclass @Access(AccessType.FIELD)
public abstract class DeactivatableAggregateRoot<T extends Identifier<?>> extends AggregateRoot<T> {

    @Column(name = "is_active", nullable = false)
    protected boolean isActive = true;

    protected DeactivatableAggregateRoot(T id) {
        super(id);
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
