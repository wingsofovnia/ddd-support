package eu.socialedge.ddd.infrastructure.persistence.jpa.domain.util;

import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.Ent1ty;
import eu.socialedge.ddd.infrastructure.persistence.jpa.domain.MindfulEnt1ty;
import lombok.val;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public final class Ent1ties {

    private Ent1ties() {
        throw new AssertionError("No instance for you");
    }

    public static Ent1ty randomEnt1ty() {
        val randString = UUID.randomUUID().toString();
        return new Ent1ty(randString);
    }

    public static MindfulEnt1ty randomMindfulEnt1ty(boolean isActive) {
        val randString = UUID.randomUUID().toString();

        return new MindfulEnt1ty(randString, isActive);
    }

    public static MindfulEnt1ty randomMindfulEnt1ty() {
        return randomMindfulEnt1ty(true);
    }

    public static Collection<Ent1ty> randomEnt1ties(int size) {
        val ent1ties = new ArrayList<Ent1ty>(size);

        for (int i = 0; i < size; i++) {
            ent1ties.add(randomEnt1ty());
        }

        return ent1ties;
    }

    public static Collection<MindfulEnt1ty> randomMindfulEnt1ties(int size, boolean isActive) {
        val ent1ties = new ArrayList<MindfulEnt1ty>(size);

        for (int i = 0; i < size; i++) {
            ent1ties.add(randomMindfulEnt1ty(isActive));
        }

        return ent1ties;
    }

    public static Collection<MindfulEnt1ty> randomMindfulEnt1ties(int size) {
        return randomMindfulEnt1ties(size, true);
    }
}
