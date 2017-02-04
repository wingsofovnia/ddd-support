package eu.socialedge.ddd.infrastructure.persistence.jpa.domain.repository;

import eu.socialedge.ddd.domain.repository.MutableRepository;
import eu.socialedge.ddd.infrastructure.persistence.jpa.SpringMutableRepository;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1ty;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1tyId;

@org.springframework.stereotype.Repository
public interface Ent1tyMutableRepository extends MutableRepository<Ent1tyId, Ent1ty>,
        SpringMutableRepository<Ent1tyId, Ent1ty> {}
