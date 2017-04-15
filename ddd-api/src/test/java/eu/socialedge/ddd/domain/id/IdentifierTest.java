package eu.socialedge.ddd.domain.id;

import eu.socialedge.ddd.domain.Entity;
import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class IdentifierTest {

    public static class CustomId extends UUIDIdentifier{}

    public static class CustomEntity extends Entity<CustomId> {}

    @Test
    public void shouldCreateEntityWithRandomUUIDIdentifier() {
        val customEntity = new CustomEntity();

        val customId = customEntity.id();
        assertFalse(customId == null);

        val customIdInternalValue = customId.value();
        assertFalse(customIdInternalValue == null);
    }
}
