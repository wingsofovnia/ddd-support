package eu.socialedge.ddd.domain.domain;

import eu.socialedge.ddd.domain.id.Identifier;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Ent1tyGenericId extends Identifier<Long> {

    public Ent1tyGenericId(Long value) {
        super(value);
    }
}
