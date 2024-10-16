/**
 * MIT License
 *
 * Copyright (c) 2024 TriumphTeam
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
