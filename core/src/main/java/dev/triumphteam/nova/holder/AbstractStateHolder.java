/**
 * MIT License
 * <p>
 * Copyright (c) 2024 TriumphTeam
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
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
import dev.triumphteam.nova.MutableState;
import dev.triumphteam.nova.State;
import dev.triumphteam.nova.builtin.EmptyState;
import dev.triumphteam.nova.builtin.SimpleMutableState;
import dev.triumphteam.nova.policy.StateMutationPolicy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    public @NotNull <T> MutableState<@NotNull T> remember(@NotNull final T value) {
        return remember(value, new StateMutationPolicy.StructuralEquality<>());
    }

    @Override
    public @NotNull <T> MutableState<@NotNull T> remember(
        final @NotNull T value,
        final @NotNull StateMutationPolicy<T> mutationPolicy
    ) {
        final var state = new SimpleMutableState<>(value, mutationPolicy);
        states.add(state);
        return state;
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
        final var state = new SimpleMutableState<>(value, mutationPolicy);
        states.add(state);
        return state;
    }

    @Override
    public @NotNull <T> ListState<T> rememberMutableList() {
        return rememberMutableList(new LinkedList<>());
    }

    @Override
    @SafeVarargs
    public final @NotNull <T> ListState<T> rememberMutableList(final T... values) {
        return rememberMutableList(ListState.of(values));
    }

    @Override
    public @NotNull <T> ListState<T> rememberMutableList(final @NotNull List<T> backing) {
        final var state = ListState.of(backing);
        states.add(state);
        return state;
    }

    protected @NotNull List<State> getStates() {
        return states;
    }
}
