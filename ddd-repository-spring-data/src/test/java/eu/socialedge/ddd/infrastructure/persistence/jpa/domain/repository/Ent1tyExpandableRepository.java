package eu.socialedge.ddd.infrastructure.persistence.jpa.domain.repository;

import eu.socialedge.ddd.domain.repository.ExpandableRepository;
import eu.socialedge.ddd.infrastructure.persistence.jpa.SpringExpandableRepository;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1ty;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1tyId;

@org.springframework.stereotype.Repository
public interface Ent1tyExpandableRepository extends ExpandableRepository<Ent1tyId, Ent1ty>,
        SpringExpandableRepository<Ent1tyId, Ent1ty> {}
