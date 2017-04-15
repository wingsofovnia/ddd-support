package eu.socialedge.ddd.util;

import lombok.val;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;

public final class Generics {

    private Generics() {
        throw new AssertionError("No instance for you");
    }

    /**
     * Creates an instance of parent's type parameter class.
     *
     * @param baseClazz       generic superclass of this baseClazz is used to create new instance
     * @param constructorArgs args for type parameter constructor
     * @param genericId       generic position in <...> list
     * @param <T>             target parameter type
     * @return new instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T constructParentTypeInstance(Class<?> baseClazz, Class<?>[] constructorArgs, int genericId) {
        try {
            val parentType = (ParameterizedType) baseClazz.getGenericSuperclass();

            if (parentType.getActualTypeArguments().length <= genericId)
                throw new IllegalArgumentException("No type parameter with genericId passed");

            val targetParentType = (Class) parentType.getActualTypeArguments()[genericId];

            val targetParentTypeConstructor = targetParentType.getConstructor(constructorArgs);
            val parentTypeInstance = targetParentTypeConstructor.newInstance((Object[]) constructorArgs);

            return (T) parentTypeInstance;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("No public constructor with args passed found", e);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Failed to init object", e);
        }
    }

    /**
     * Creates an instance of parent's type parameter class using default constructor
     *
     * @param baseClazz generic superclass of this baseClazz is used to create new instance
     * @param genericId generic position in <...> list
     * @param <T>       target parameter type
     * @return new instance
     */
    public static <T> T constructParentTypeInstance(Class<?> baseClazz, int genericId) {
        if (isInnerClass(baseClazz))
            throw new IllegalArgumentException("Inner classes are not supported");

        return constructParentTypeInstance(baseClazz, new Class<?>[]{}, genericId);
    }

    private static boolean isInnerClass(Class<?> clazz) {
        return clazz.isMemberClass() && !Modifier.isStatic(clazz.getModifiers());
    }
}
