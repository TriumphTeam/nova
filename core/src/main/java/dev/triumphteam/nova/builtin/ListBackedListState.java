package dev.triumphteam.nova.builtin;

import dev.triumphteam.nova.AbstractState;
import dev.triumphteam.nova.ListState;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class ListBackedListState<T> extends AbstractState implements ListState<T> {

    private final List<T> backing;

    public ListBackedListState(final @NotNull List<T> backing) {
        this.backing = backing;
    }

    @Override
    public boolean add(final T t) {
        final var added = backing.add(t);
        if (added) trigger();
        return added;
    }

    @Override
    public boolean remove(final Object o) {
        final var removed = backing.remove(o);
        if (removed) trigger();
        return removed;
    }

    @Override
    public boolean addAll(@NotNull final Collection<? extends T> c) {
        final var added = backing.addAll(c);
        if (added) trigger();
        return added;
    }

    @Override
    public boolean addAll(final int index, @NotNull final Collection<? extends T> c) {
        final var added = backing.addAll(index, c);
        if (added) trigger();
        return added;
    }

    @Override
    public boolean removeAll(@NotNull final Collection<?> c) {
        final var removed = backing.removeAll(c);
        if (removed) trigger();
        return removed;
    }

    @Override
    public boolean retainAll(@NotNull final Collection<?> c) {
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
    public boolean containsAll(@NotNull final Collection<?> c) {
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
