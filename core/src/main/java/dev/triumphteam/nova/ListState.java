package dev.triumphteam.nova;

import dev.triumphteam.nova.builtin.ListBackedListState;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

public interface ListState<T> extends State, List<T>, RandomAccess {


    static <T> @NotNull ListState<T> of() {
        return of(new LinkedList<>());
    }

    @SafeVarargs
    static <T> @NotNull ListState<T> of(final T... values) {
        return of(List.of(values));
    }

    static <T> @NotNull ListState<T> of(final @NotNull List<T> backing) {
        return new ListBackedListState<>(backing);
    }
}
