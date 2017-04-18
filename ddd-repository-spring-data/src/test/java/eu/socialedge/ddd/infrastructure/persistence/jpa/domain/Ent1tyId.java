package eu.socialedge.ddd.infrastructure.persistence.jpa.domain;

import eu.socialedge.ddd.domain.id.Identifier;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(force = true)
public class Ent1tyId extends Identifier<Long> {
    public Ent1tyId(Long value) {
        super(value);
    }

    public static Ent1tyId of(Long id) {
        return new Ent1tyId(id);
    }
}
