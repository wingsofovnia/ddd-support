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
        val randEntity = persistRandomEntity();
        val randEntityId = randEntity.id();

        assertTrue(entityRepository.contains(randEntityId));
    }

    @Test
    public void shouldReturnCorrectSize() {
        val randEntities = persistRandomEnt1ties(5);

        assertFalse(entityRepository.isEmpty());
        assertEquals(randEntities.size(), (int) entityRepository.size());
    }

    @Test
    public void shouldRetrieveEntityCorrectly() {
        val randEntity = persistRandomEntity();
        val randEntityId = randEntity.id();

        val repoEntityOpt = entityRepository.get(randEntityId);

        assertTrue(repoEntityOpt.isPresent());
        assertEquals(randEntity, repoEntityOpt.get());
    }

    @Test
    public void shouldRetrieveEntityListCorrectly() {
        val randEntities = persistRandomEnt1ties(5);

        val repoEntities = entityRepository.list();

        assertFalse(repoEntities.isEmpty());
        assertEquals(randEntities.size(), (int) entityRepository.size());
        assertTrue(randEntities.containsAll(repoEntities));
        assertTrue(repoEntities.containsAll(randEntities));
    }

    private Ent1ty persistRandomEntity() {
        val randEntity = randomEnt1ty();

        entityManager.persist(randEntity);
        return randEntity;
    }

    private Collection<Ent1ty> persistRandomEnt1ties(int size) {
        val randEntities = randomEnt1ties(size);

        randEntities.forEach(entityManager::persist);
        return randEntities;
    }
}
