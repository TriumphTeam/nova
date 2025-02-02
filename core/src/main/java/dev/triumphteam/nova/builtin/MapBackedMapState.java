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
package dev.triumphteam.nova.builtin;

import dev.triumphteam.nova.AbstractState;
import dev.triumphteam.nova.MapState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public final class MapBackedMapState<K, V> extends AbstractState implements MapState<K, V> {

    private final Map<K, V> backing;

    public MapBackedMapState(final @NotNull Map<K, V> backing) {
        this.backing = backing;
    }

    @Override
    public @Nullable V put(final K key, final V value) {
        final var val = backing.put(key, value);
        trigger();
        return val;
    }

    @Override
    public V remove(final Object key) {
        final var val = backing.remove(key);
        trigger();
        return val;
    }

    @Override
    public void putAll(final @NotNull Map<? extends K, ? extends V> m) {
        backing.putAll(m);
        trigger();
    }

    @Override
    public void clear() {
        backing.clear();
        trigger();
    }

    @Override
    public int size() {
        return backing.size();
    }

    @Override
    public boolean isEmpty() {
        return backing.isEmpty();
    }

    @Override
    public boolean containsKey(final Object key) {
        return backing.containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return backing.containsValue(value);
    }

    @Override
    public V get(final Object key) {
        return backing.get(key);
    }

    @Override
    public @NotNull Set<K> keySet() {
        return backing.keySet();
    }

    @Override
    public @NotNull Collection<V> values() {
        return backing.values();
    }

    @Override
    public @NotNull Set<Entry<K, V>> entrySet() {
        return backing.entrySet();
    }
}
