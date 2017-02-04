package eu.socialedge.ddd.infrastructure.persistence.jpa;

import eu.socialedge.ddd.domain.AggregateRoot;
import eu.socialedge.ddd.domain.DeactivatableAggregateRoot;
import eu.socialedge.ddd.domain.DeactivatableAggregateRoot_;
import eu.socialedge.ddd.domain.Identifier;
import eu.socialedge.ddd.domain.repository.MindfulRepository;
import eu.socialedge.ddd.domain.repository.RepositoryException;
import lombok.val;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static eu.socialedge.ddd.infrastructure.persistence.jpa.SpringRepository.exec;

/**
 * A Spring Data JPA based {@link eu.socialedge.ddd.domain.repository.MindfulRepository} implementation
 *
 * @param <ID> concrete {@link Identifier} type implementation
 * @param <T> {@link AggregateRoot} type implementation
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface SpringMindfulRepository<ID extends Identifier<?>, T extends DeactivatableAggregateRoot<ID>>
        extends MindfulRepository<ID, T>, SpringExpandableRepository<ID, T>, JpaSpecificationExecutor<T> {

    @Override
    default boolean containsActive(ID id) {
        val entityOpt = get(id);
        return entityOpt.map(DeactivatableAggregateRoot::isActive).orElse(false);
    }

    @Override
    @Transactional
    default void activate(ID id) {
        val entity = get(id).orElseThrow(()
            -> new RepositoryException("Cannot activate entity: Entity not found"));

        entity.activate();
    }

    @Override
    @Transactional
    default void activate(Iterable<ID> entityIds) {
        entityIds.forEach(this::activate);
    }

    @Override
    @Transactional
    default void deactivate(ID id) {
        val entity = get(id).orElseThrow(()
            -> new RepositoryException("Cannot deactivate entity: Entity not found"));

        entity.deactivate();
    }

    @Override
    @Transactional
    default void deactivate(Iterable<ID> entityIds) {
        entityIds.forEach(this::deactivate);
    }

    @Override
    default Optional<T> getActive(ID id) {
        val entityOpt = get(id);

        if (!entityOpt.isPresent() || !entityOpt.get().isActive())
            return Optional.empty();

        return entityOpt;
    }

    @Override
    default Collection<T> listActive() {
        return exec(() -> findAll((root, query, cb)
            -> cb.equal(root.<Boolean>get(DeactivatableAggregateRoot_.isActive), true)));
    }
}
