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

import dev.triumphteam.nova.builtin.MapBackedMapState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link Map} representation of a state.
 * An update is triggered when the map entries change.
 *
 * @param <K> The type of the key of the map.
 * @param <V> The type of the value of the map.
 */
public interface MapState<K, V> extends State, Map<K, V> {

    /**
     * Creates a new {@link MapState} with a backing {@link HashMap}.
     *
     * @param <K> The type of the key of the map.
     * @param <V> The type of the value of the map.
     * @return A new {@link MapState}.
     */
    static <K, V> @NotNull MapState<K, V> of() {
        return of(new HashMap<>());
    }

    /**
     * Creates a new {@link MapState} with the given map as its backing.
     *
     * @param backing The backing {@link Map} for the state to use.
     * @param <K>     The type of the key of the map.
     * @param <V>     The type of the value of the map.
     * @return A new {@link MapState}.
     */
    static <K, V> @NotNull MapState<K, V> of(final @NotNull Map<K, V> backing) {
        return new MapBackedMapState<>(backing);
    }
}
