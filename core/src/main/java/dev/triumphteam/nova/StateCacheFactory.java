package dev.triumphteam.nova;

import com.google.common.cache.CacheBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Queue;
import java.util.function.Supplier;

/**
 * Cache factory for states to use.
 * By default, it uses guava's cache, but has a method to allow overriding it and using something like caffeine instead.
 */
public final class StateCacheFactory {

    private static Supplier<Map<Object, Queue<Runnable>>> factory = defaultFactory();

    private StateCacheFactory() {}

    /**
     * Set the current factory.
     * <strong>MAKE SURE THAT THE CACHE HAS WEAK KEYS.</strong>
     *
     * @param factory The provided factory to be used by all states.
     */
    public static void setFactory(final @NotNull Supplier<@NotNull Map<@NotNull Object, @NotNull Queue<@NotNull Runnable>>> factory) {
        StateCacheFactory.factory = factory;
    }

    /**
     * Creates a new cache.
     *
     * @return A map that is cache backed. Should have weak keys.
     */
    public static @NotNull Map<@NotNull Object, @NotNull Queue<@NotNull Runnable>> create() {
        return factory.get();
    }

    private static @NotNull Supplier<@NotNull Map<@NotNull Object, @NotNull Queue<@NotNull Runnable>>> defaultFactory() {
        return () -> CacheBuilder.newBuilder()
            .weakKeys()
            .<@NotNull Object, @NotNull Queue<@NotNull Runnable>>build()
            .asMap();
    }
}
