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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * A map backed container for state listeners.
 * This container uses a map with weak keys, so instances of the {@link Stateful} can prevent
 * values from being garbage collected correctly.
 */
public final class StateListenerContainer {

    /**
     * Listeners cache.
     * The keys of the map are weak.
     * The value of the map is a {@link ConcurrentLinkedQueue}.
     */
    private final Map<Stateful, Queue<Runnable>> listeners = createListenerMap();

    /**
     * Creates a map to be used for the listeners.
     *
     * @return A {@link ConcurrentMap} with weak keys.
     */
    private static Map<Stateful, Queue<Runnable>> createListenerMap() {
        final Cache<Stateful, Queue<Runnable>> cache = CacheBuilder.newBuilder()
            .weakKeys()
            .build();

        return cache.asMap();
    }

    /**
     * Adds listener tied to the {@link Stateful} lifecycle.
     *
     * @param stateful The stateful object to be used as the reference.
     * @param listener The listener to run when a state is triggered.
     */
    public void addListener(final @NotNull Stateful stateful, final @NotNull Runnable listener) {
        listeners.computeIfAbsent(stateful, ignored -> new ConcurrentLinkedQueue<>()).add(listener);
    }

    /**
     * Triggers all listeners that this state uses.
     */
    public void triggerAll() {
        listeners.values().forEach(listeners -> listeners.forEach(Runnable::run));
    }
}
