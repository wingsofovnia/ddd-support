package eu.socialedge.ddd.infrastructure.persistence.jpa;

import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.repository.Ent1tyMindfulRepository;
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
import java.util.stream.Stream;

import static eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util.Ent1ties.randomMindfulEnt1ties;
import static eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util.Ent1ties.randomMindfulEnt1ty;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SpringRepositoryTestConfig.class)
public class SpringMindfulRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private Ent1tyMindfulRepository entityRepository;

    @Before
    public void setUp() {
        entityManager.clear();
        entityManager.flush();
    }

    @Test
    public void shouldActivateEntityCorrectly() {
        val entity = randomMindfulEnt1ty(false);
        entityRepository.add(entity);

        entityRepository.activate(entity.id());

        assertTrue(entityRepository.get(entity.id()).get().isActive());
    }

    @Test
    public void shouldDeactivateEntityCorrectly() {
        val entity = randomMindfulEnt1ty(true);
        entityRepository.add(entity);

        entityRepository.deactivate(entity.id());

        assertFalse(entityRepository.get(entity.id()).get().isActive());
    }

    @Test
    public void shouldListOnlyActiveEntities() {
        val inactiveRandEntity = randomMindfulEnt1ty(false);
        entityRepository.add(inactiveRandEntity);

        val activeRandEntities = randomMindfulEnt1ties(5);
        entityRepository.add(activeRandEntities);

        val activeSavedEntities = entityRepository.listActive();

        assertEquals(activeRandEntities.size(), activeSavedEntities.size());
        assertTrue(activeSavedEntities.containsAll(activeRandEntities));
    }

    @Test
    public void shouldCheckForEntityExistenceCorrectly() {
        val randActiveEntity = randomMindfulEnt1ty();
        val randInactiveEntity = randomMindfulEnt1ty(false);
        entityRepository.add(randActiveEntity);
        entityRepository.add(randInactiveEntity);

        assertTrue(entityRepository.containsActive(randActiveEntity.id()));
        assertFalse(entityRepository.containsActive(randInactiveEntity.id()));
        assertTrue(entityRepository.contains(randActiveEntity.id()));
    }

    @Test
    public void shouldListEntitiesEntities() {
        val randActiveEntities = randomMindfulEnt1ties(5);
        val randInactiveEntities = randomMindfulEnt1ties(5, false);
        entityRepository.add(randActiveEntities);
        entityRepository.add(randInactiveEntities);

        val savedActiveEntities = entityRepository.listActive();
        assertEquals(randActiveEntities.size(), savedActiveEntities.size());
        assertTrue(savedActiveEntities.containsAll(randActiveEntities));

        val savedAllEntities = entityRepository.list();
        val entitiesUnion = Stream
            .concat(randActiveEntities.stream(), randInactiveEntities.stream())
            .collect(Collectors.toList());

        assertEquals(entitiesUnion.size(), savedAllEntities.size());
        assertTrue(entitiesUnion.containsAll(savedAllEntities));
    }
}
