package eu.socialedge.ddd.util;

import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GenericsTest {

    static class TargetClassToBeCreated {
        public String body;
        public TargetClassToBeCreated() {
            body = "created";
        }
    }

    static class GenericSuperclass<T> {}

    @Test
    public void shouldConstructParentTypeInstance() {
        class Generic extends GenericSuperclass<TargetClassToBeCreated> {
            public Generic() {
                val createdInstance = Generics.<TargetClassToBeCreated>constructParentTypeInstance(this.getClass(), 0);

                assertTrue(createdInstance.getClass().equals(TargetClassToBeCreated.class));
                assertEquals(createdInstance.body, "created");
            }
        }
    }
}
