package eu.socialedge.ddd.infrastructure.persistence.jpa;

import eu.socialedge.ddd.domain.Entity;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.repository.Ent1tyMutableRepository;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util.Ent1ties.randomEnt1ties;
import static eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util.Ent1ties.randomEnt1ty;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SpringRepositoryTestConfig.class)
public class SpringMutableRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private Ent1tyMutableRepository entityRepository;

    @Before
    public void setUp() {
        entityManager.clear();
        entityManager.flush();
    }

    @Test
    public void shouldRemoveEntityCorrectly() {
        val entity = randomEnt1ty();

        entityRepository.add(entity);

        entityRepository.remove(entity);
        assertTrue(entityRepository.isEmpty());
        assertTrue(entityRepository.size() == 0);
    }

    @Test
    public void shouldRemoveEntityByIdCorrectly() {
        val entity = randomEnt1ty();
        val entityId = entity.id();

        entityRepository.add(entity);

        entityRepository.remove(entityId);
        assertTrue(entityRepository.isEmpty());
        assertTrue(entityRepository.size() == 0);
    }

    @Test
    public void shouldRemoveEntitiesByIdListCorrectly() {
        val entities = randomEnt1ties(5);
        val entitiesId = entities.stream().map(Entity::id).collect(Collectors.toList());

        entityRepository.add(entities);

        entityRepository.remove(entitiesId);
        assertTrue(entityRepository.isEmpty());
        assertTrue(entityRepository.size() == 0);
    }

    @Test
    public void shouldClearCorrectly() {
        entityRepository.add(randomEnt1ties(5));

        entityRepository.clear();
        assertTrue(entityRepository.isEmpty());
        assertTrue(entityRepository.size() == 0);
    }
}
