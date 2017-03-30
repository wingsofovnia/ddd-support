package eu.socialedge.ddd.domain.id;

import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A default implementation of {@link GeneratableIdentifier} with Long used
 * as internal value.
 */
@NoArgsConstructor
public abstract class LongIdentifier extends GeneratableIdentifier<Long> {

    private static final Long GEN_RAND_MIN_VALUE = 1L;
    private static final Long GEN_RAND_MAX_VALUE = Long.MAX_VALUE;

    public LongIdentifier(Long value) {
        super(value);
    }

    @Override
    protected Long generate() {
        return ThreadLocalRandom.current().nextLong(GEN_RAND_MIN_VALUE, GEN_RAND_MAX_VALUE);
    }
}
