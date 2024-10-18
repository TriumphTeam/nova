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
import dev.triumphteam.nova.policy.StateMutationPolicy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface StateHolder {

    /**
     * Make the {@link StateHolder} remember an empty state.
     *
     * @return A new {@link EmptyState}.
     */
    @NotNull
    State remember();

    /**
     * Make the {@link StateHolder} remember the {@link S} state.
     *
     * @param state A state.
     * @return The same state passed.
     */
    @NotNull
    <S extends State> S remember(final @NotNull S state);

    /**
     * Make the {@link StateHolder} remember the {@link T} value.
     * Using {@link StateMutationPolicy.StructuralEquality}.
     *
     * @param value The default value of the state.
     * @param <T>   The type of the value.
     * @return The newly created {@link MutableState}.
     */
    <T> @NotNull MutableState<@NotNull T> remember(final @NotNull T value);

    /**
     * Make the {@link StateHolder} remember the {@link T} value.
     * Uses the given {@link StateMutationPolicy} for equivalence check.
     *
     * @param value          The default value of the state.
     * @param mutationPolicy The mutation policy to use.
     * @param <T>            The type of the value.
     * @return The newly created {@link MutableState}.
     */
    <T> @NotNull MutableState<@NotNull T> remember(
        final @NotNull T value,
        final @NotNull StateMutationPolicy mutationPolicy
    );

    /**
     * Make the {@link StateHolder} remember the {@link T} value.
     * Using {@link StateMutationPolicy.StructuralEquality}.
     * In this case, the value is nullable.
     *
     * @param value The default value of the state.
     * @param <T>   The type of the value.
     * @return The newly created {@link MutableState}.
     */
    <T> @NotNull MutableState<@Nullable T> rememberNullable(final @Nullable T value);

    /**
     * Make the {@link StateHolder} remember the {@link T} value.
     * In this case, the value is nullable.
     * Uses the given {@link StateMutationPolicy} for equivalence check.
     *
     * @param value          The default value of the state.
     * @param mutationPolicy The mutation policy to use.
     * @param <T>            The type of the value.
     * @return The newly created {@link MutableState}.
     */
    <T> @NotNull MutableState<@Nullable T> rememberNullable(
        final @Nullable T value,
        final @NotNull StateMutationPolicy mutationPolicy
    );

    <T> @NotNull ListState<T> rememberMutableList();

    <T> @NotNull ListState<T> rememberMutableList(final @NotNull List<T> backing);

    <T> @NotNull ListState<T> rememberMutableList(final T... values);
}
