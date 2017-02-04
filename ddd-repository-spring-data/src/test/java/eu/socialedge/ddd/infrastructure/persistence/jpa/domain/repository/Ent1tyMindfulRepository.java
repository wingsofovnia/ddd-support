package eu.socialedge.ddd.infrastructure.persistence.jpa.domain.repository;

import eu.socialedge.ddd.domain.repository.MindfulRepository;
import eu.socialedge.ddd.infrastructure.persistence.jpa.SpringMindfulRepository;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1tyId;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.MindfulEnt1ty;

@org.springframework.stereotype.Repository
public interface Ent1tyMindfulRepository extends MindfulRepository<Ent1tyId, MindfulEnt1ty>,
        SpringMindfulRepository<Ent1tyId, MindfulEnt1ty> {}
