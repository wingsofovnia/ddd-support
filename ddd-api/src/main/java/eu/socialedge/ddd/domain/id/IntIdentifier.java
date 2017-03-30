package eu.socialedge.ddd.domain.id;

import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A default implementation of {@link GeneratableIdentifier} with Integer used
 * as internal value.
 */
@NoArgsConstructor
public abstract class IntIdentifier extends GeneratableIdentifier<Integer> {

    private static final Integer GEN_RAND_MIN_VALUE = 1;
    private static final Integer GEN_RAND_MAX_VALUE = Integer.MAX_VALUE;

    public IntIdentifier(Integer value) {
        super(value);
    }

    @Override
    protected Integer generate() {
        return ThreadLocalRandom.current().nextInt(GEN_RAND_MIN_VALUE, GEN_RAND_MAX_VALUE);
    }
}
