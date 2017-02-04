package eu.socialedge.ddd.infrastructure.persistence.jpa.domain;

import eu.socialedge.ddd.domain.AggregateRoot;
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
public class Ent1ty extends AggregateRoot<Ent1tyId> implements Cloneable {

    @Column(nullable = false)
    public String field;

    public Ent1ty(Ent1tyId id, String field) {
        super(id);
        this.field = field;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Ent1ty(id, field);
    }
}
