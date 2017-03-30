package eu.socialedge.ddd.domain;

import eu.socialedge.ddd.domain.domain.Ent1tyGeneratableIdAggregationRoot;
import eu.socialedge.ddd.domain.domain.Ent1tyGenericIdAggregationRoot;
import lombok.val;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AggregateRootTest {

    @Test
    public void shouldGenerateIdForAggRootCorrectly() {
        val ar = new Ent1tyGeneratableIdAggregationRoot();
        val arUuid = ar.id();

        assertNotNull(arUuid);
        UUID.fromString(arUuid.toString());
    }

    @Test
    public void shouldCreateAggRootWithNonGeneratableIdCorrectly() {
        val ar = new Ent1tyGenericIdAggregationRoot();
        val arrId = ar.id();

        assertNull(arrId.value());
    }
}
