package eu.socialedge.ddd.domain.domain;

import eu.socialedge.ddd.domain.AggregateRoot;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Ent1tyGenericIdAggregationRoot extends AggregateRoot<Ent1tyGenericId> {

    public Ent1tyGenericIdAggregationRoot(Ent1tyGenericId id) {
        super(id);
    }
}
