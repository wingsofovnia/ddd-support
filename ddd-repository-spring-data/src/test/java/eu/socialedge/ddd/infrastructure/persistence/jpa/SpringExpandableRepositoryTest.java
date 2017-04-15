package eu.socialedge.ddd.infrastructure.persistence.jpa;

import eu.socialedge.ddd.domain.repository.RepositoryException;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1ty;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.repository.Ent1tyExpandableRepository;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util.Ent1ties.randomEnt1ties;
import static eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util.Ent1ties.randomEnt1ty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SpringRepositoryTestConfig.class)
public class SpringExpandableRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private Ent1tyExpandableRepository entityRepository;

    @Before
    public void setUp() {
        entityManager.clear();
        entityManager.flush();
    }

    @Test
    public void shouldAddEnt1tyCorrectly() {
        val entity = randomEnt1ty();

        entityRepository.add(entity);
        assertEquals(1L, entityRepository.size());

        val savedEntityOpt = entityRepository.get(entity.id());

        assertTrue(savedEntityOpt.isPresent());
        assertEquals(entity, savedEntityOpt.get());
    }

    @Test
    public void shouldAddEnt1tyListCorrectly() {
        val entities = randomEnt1ties(5);

        entityRepository.add(entities);

        val savedEntities = entityRepository.list();

        assertTrue(entityRepository.size() == entities.size());
        assertTrue(entities.containsAll(savedEntities));
    }

    @Test
    @Transactional
    public void shouldListenEnt1tyChanges() {
        val entity = randomEnt1ty();

        entityRepository.add(entity);

        entity.field = "FAILED?";

        val savedEntity = entityRepository.get(entity.id()).get();
        assertEquals(entity, savedEntity);
    }

    @Test(expected = RepositoryException.class)
    public void shouldNotOverrideEntityWithTheSameId() throws CloneNotSupportedException {
        val randOrigEntity = randomEnt1ty();
        val randCopyEntity = (Ent1ty) randOrigEntity.clone();

        entityRepository.add(randOrigEntity);
        entityRepository.add(randCopyEntity);
    }
}
