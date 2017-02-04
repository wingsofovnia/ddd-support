package eu.socialedge.ddd.infrastructure.persistence.jpa.domain.repository;

import eu.socialedge.ddd.domain.repository.Repository;
import eu.socialedge.ddd.infrastructure.persistence.jpa.SpringRepository;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1ty;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1tyId;

@org.springframework.stereotype.Repository
public interface Ent1tyRepository extends Repository<Ent1tyId, Ent1ty>, SpringRepository<Ent1tyId, Ent1ty> {}
