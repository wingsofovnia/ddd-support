package eu.socialedge.ddd.infrastructure.persistence.jpa.domain;

import eu.socialedge.ddd.domain.id.LongIdentifier;

import javax.persistence.Embeddable;

@Embeddable
public class Ent1tyId extends LongIdentifier {

    public Ent1tyId() {}

    public Ent1tyId(Long value) {
        super(value);
    }

    public static Ent1tyId of(Long id) {
        return new Ent1tyId(id);
    }
}
