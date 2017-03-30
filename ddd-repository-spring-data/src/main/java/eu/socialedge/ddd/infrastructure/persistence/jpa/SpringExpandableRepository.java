package eu.socialedge.ddd.infrastructure.persistence.jpa;

import eu.socialedge.ddd.domain.AggregateRoot;
import eu.socialedge.ddd.domain.id.Identifier;
import eu.socialedge.ddd.domain.repository.ExpandableRepository;
import eu.socialedge.ddd.domain.repository.RepositoryException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static eu.socialedge.ddd.infrastructure.persistence.jpa.SpringRepository.exec;

/**
 * A Spring Data JPA based {@link ExpandableRepository} implementation
 *
 * @param <ID> concrete {@link Identifier} type implementation
 * @param <T> {@link AggregateRoot} type implementation
 */
@NoRepositoryBean
@Transactional(readOnly = true)
public interface SpringExpandableRepository<ID extends Identifier<?>, T extends AggregateRoot<ID>>
        extends ExpandableRepository<ID, T>, SpringRepository<ID, T> {

    @Override
    @Transactional
    default void add(T entity) {
        if (contains(entity))
            throw new RepositoryException("Entity already exists");

        exec(() -> save(entity));
    }

    @Override
    @Transactional
    default void add(Collection<T> entities) {
        entities.forEach(entity -> exec(() -> save(entity)));
    }
}
