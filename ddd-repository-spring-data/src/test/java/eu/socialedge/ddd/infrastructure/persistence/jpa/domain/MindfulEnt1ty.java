package eu.socialedge.ddd.infrastructure.persistence.jpa.domain;

import eu.socialedge.ddd.domain.DeactivatableAggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor(force = true)
public class MindfulEnt1ty extends DeactivatableAggregateRoot<Ent1tyId> {

    @Column(nullable = false)
    public String field;

    public MindfulEnt1ty(String field, boolean isActive) {
        super(isActive);
        this.field = field;
    }

    public MindfulEnt1ty(String field) {
        this(field, true);
    }
}
