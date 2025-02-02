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
package dev.triumphteam.nova.holder;

import dev.triumphteam.nova.ListState;
import dev.triumphteam.nova.MapState;
import dev.triumphteam.nova.MutableState;
import dev.triumphteam.nova.State;
import dev.triumphteam.nova.builtin.EmptyState;
import dev.triumphteam.nova.builtin.SimpleMutableState;
import dev.triumphteam.nova.policy.StateMutationPolicy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractStateHolder implements StateHolder {

    private final List<State> states = new ArrayList<>();

    @Override
    public @NotNull State remember() {
        return remember(new EmptyState());
    }

    @Override
    public <S extends State> @NotNull S remember(final @NotNull S state) {
        states.add(state);
        return state;
    }

    @Override
    public @NotNull <T> MutableState<@NotNull T> remember(final @NotNull T value) {
        return remember(value, new StateMutationPolicy.StructuralEquality<>());
    }

    @Override
    public @NotNull <T> MutableState<@NotNull T> remember(
        final @NotNull T value,
        final @NotNull StateMutationPolicy<T> mutationPolicy
    ) {
        return remember(new SimpleMutableState<>(value, mutationPolicy));
    }

    @Override
    public @NotNull <T> MutableState<@Nullable T> rememberNullable(final @Nullable T value) {
        return rememberNullable(value, new StateMutationPolicy.StructuralEquality<>());
    }

    @Override
    public @NotNull <T> MutableState<@Nullable T> rememberNullable(
        final @Nullable T value,
        final @NotNull StateMutationPolicy<T> mutationPolicy
    ) {
        return remember(new SimpleMutableState<>(value, mutationPolicy));
    }

    @Override
    public @NotNull <T> List<T> rememberList() {
        return remember(ListState.of());
    }

    @Override
    @SafeVarargs
    public final @NotNull <T> List<T> rememberList(final T... values) {
        return remember(ListState.of(values));
    }

    @Override
    public @NotNull <T> List<T> rememberList(final @NotNull List<T> backing) {
        return remember(ListState.of(backing));
    }

    @Override
    public @NotNull <K, V> Map<K, V> rememberMap() {
        return remember(MapState.of());
    }

    @Override
    public @NotNull <K, V> Map<K, V> rememberMap(final @NotNull Map<K, V> backing) {
        return remember(MapState.of(backing));
    }

    protected @NotNull List<State> getStates() {
        return states;
    }
}
