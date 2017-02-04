package eu.socialedge.ddd.infrastructure.persistence.jpa;

import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1ty;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.repository.Ent1tyRepository;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util.Ent1ties.randomEnt1ties;
import static eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util.Ent1ties.randomEnt1ty;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SpringRepositoryTestConfig.class)
public class SpringRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private Ent1tyRepository entityRepository;

    @Before
    public void setUp() {
        entityManager.clear();
        entityManager.flush();
    }

    @Test
    public void shouldContainSavedEntity() {
        val entity = persistRandomEntity();
        val entityId = entity.id();

        assertTrue(entityRepository.contains(entityId));
    }

    @Test
    public void shouldReturnCorrectSize() {
        val entities = persistRandomEnt1ties(5);

        assertFalse(entityRepository.isEmpty());
        assertEquals(entities.size(), (int) entityRepository.size());
    }

    @Test
    public void shouldRetrieveEntityCorrectly() {
        val entity = persistRandomEntity();
        val entityId = entity.id();

        val savedEntityOpt = entityRepository.get(entityId);

        assertTrue(savedEntityOpt.isPresent());
        assertEquals(entity, savedEntityOpt.get());
    }

    @Test
    public void shouldRetrieveEntityListCorrectly() {
        val entities = persistRandomEnt1ties(5);

        val savedEntities = entityRepository.list();

        assertFalse(savedEntities.isEmpty());
        assertEquals(entities.size(), (int) entityRepository.size());
        assertTrue(entities.containsAll(savedEntities));
        assertTrue(savedEntities.containsAll(entities));
    }

    private Ent1ty persistRandomEntity() {
        val entity = randomEnt1ty();

        entityManager.persist(entity);
        return entity;
    }

    private Collection<Ent1ty> persistRandomEnt1ties(int size) {
        val entities = randomEnt1ties(size);

        entities.forEach(entityManager::persist);
        return entities;
    }
}
