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
import dev.triumphteam.nova.ListState;
import dev.triumphteam.nova.MapState;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * A {@link ListState} implementation backed by a provided {@link List} implementation.
 * An update is triggered when the list elements change.
 */
public final class ListBackedListState<T> extends AbstractState implements ListState<T> {

    private final List<T> backing;

    public ListBackedListState(final @NotNull List<T> backing) {
        this.backing = backing;
    }

    @Override
    public boolean add(final T t) {
        backing.add(t);
        trigger();
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        final var removed = backing.remove(o);
        if (removed) trigger();
        return removed;
    }

    @Override
    public boolean addAll(final @NotNull Collection<? extends T> c) {
        final var added = backing.addAll(c);
        if (added) trigger();
        return added;
    }

    @Override
    public boolean addAll(final int index, final @NotNull Collection<? extends T> c) {
        final var added = backing.addAll(index, c);
        if (added) trigger();
        return added;
    }

    @Override
    public boolean removeAll(final @NotNull Collection<?> c) {
        final var removed = backing.removeAll(c);
        if (removed) trigger();
        return removed;
    }

    @Override
    public boolean retainAll(final @NotNull Collection<?> c) {
        final var retained = backing.retainAll(c);
        if (retained) trigger();
        return retained;
    }

    @Override
    public T set(final int index, final T element) {
        final var updated = backing.set(index, element);
        trigger();
        return updated;
    }

    @Override
    public void clear() {
        backing.clear();
        trigger();
    }

    @Override
    public T remove(final int index) {
        final var updated = backing.remove(index);
        trigger();
        return updated;
    }

    @Override
    public void add(final int index, final T element) {
        backing.add(index, element);
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
    public boolean contains(final Object o) {
        return backing.contains(o);
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return backing.iterator();
    }

    @Override
    public @NotNull Object @NotNull [] toArray() {
        return backing.toArray();
    }

    @Override
    public <A> @NotNull A @NotNull [] toArray(final @NotNull A @NotNull [] a) {
        return backing.toArray(a);
    }

    @Override
    public boolean containsAll(final @NotNull Collection<?> c) {
        return backing.containsAll(c);
    }

    @Override
    public T get(final int index) {
        return backing.get(index);
    }

    @Override
    public int indexOf(final Object o) {
        return backing.indexOf(o);
    }

    @Override
    public int lastIndexOf(final Object o) {
        return backing.lastIndexOf(o);
    }

    @Override
    public @NotNull ListIterator<T> listIterator() {
        return backing.listIterator();
    }

    @Override
    public @NotNull ListIterator<T> listIterator(final int index) {
        return backing.listIterator(index);
    }

    @Override
    public @NotNull List<T> subList(final int fromIndex, final int toIndex) {
        return backing.subList(fromIndex, toIndex);
    }
}
