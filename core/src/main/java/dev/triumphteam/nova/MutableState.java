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
package dev.triumphteam.nova;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dev.triumphteam.nova.builtin.SimpleMutableState;
import dev.triumphteam.nova.policy.StateMutationPolicy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * A representation of a {@link State} that is mutable.
 * Modifying value of type {@link T} will trigger the component associated with this state to re-render.
 * Setting the value to an equal value as the current, may or may not trigger an update,
 * all depending on the implementation of the used {@link MutableState}.
 * Same for the nullability of the value.
 *
 * @param <T> The type the state accepts.
 * @see AbstractMutableState
 * @see SimpleMutableState
 */
public interface MutableState<T> extends State {

    /**
     * Creates a new mutable state with the given value.
     * Using {@link StateMutationPolicy.StructuralEquality}.
     *
     * @param value The starting value of the state.
     * @param <T>   The type of the value.
     * @return A new {@link MutableState}.
     */
    static <T> @NotNull MutableState<@NotNull T> of(final @NotNull T value) {
        return of(value, new StateMutationPolicy.StructuralEquality<>());
    }

    /**
     * Creates a new mutable state with the given value.
     *
     * @param value  The starting value of the state.
     * @param policy The {@link StateMutationPolicy} to be used.
     * @param <T>    The type of the value.
     * @return A new {@link MutableState}.
     */
    static <T> @NotNull MutableState<@NotNull T> of(final @NotNull T value, final @NotNull StateMutationPolicy<T> policy) {
        return new SimpleMutableState<>(value, policy);
    }

    /**
     * Creates a new mutable nullable state defaulted to {@code null}.
     *
     * @param <T> The type of the value.
     * @return A new {@link MutableState}.
     */
    static <T> @NotNull MutableState<@Nullable T> ofNullable() {
        return ofNullable(null);
    }

    /**
     * Creates a new mutable nullable state with the given value.
     * Using {@link StateMutationPolicy.StructuralEquality}.
     *
     * @param value The starting nullable value of the state.
     * @param <T>   The type of the value.
     * @return A new {@link MutableState}.
     */
    static <T> @NotNull MutableState<@Nullable T> ofNullable(final @Nullable T value) {
        return ofNullable(value, new StateMutationPolicy.StructuralEquality<>());
    }

    /**
     * Creates a new mutable nullable state with the given value.
     *
     * @param value  The starting nullable value of the state.
     * @param policy The {@link StateMutationPolicy} to be used.
     * @param <T>    The type of the value.
     * @return A new {@link MutableState}.
     */
    static <T> @NotNull MutableState<@Nullable T> ofNullable(final @Nullable T value, final @NotNull StateMutationPolicy<T> policy) {
        return new SimpleMutableState<>(value, policy);
    }

    /**
     * Gets the current value of the state.
     * The nullability of this value depends on the value passed.
     *
     * @return The value of the state.
     */
    T get();

    /**
     * Set a new value to the state.
     * This may or may not trigger a component to re-draw.
     *
     * @param value The new value of the state.
     */
    void set(final T value);

    /**
     * Updates the value of the state.
     * The function provides the previous value and will {@link MutableState#set(Object)} the returning value to the state.
     *
     * @param update The update function to update the value of this state.
     * @return The new value.
     */
    @CanIgnoreReturnValue
    T update(final @NotNull Function<T, T> update);

    /**
     * Which mutation policy to be used by this state.
     * The mutation policy is not used outside the state itself,
     * so it can be ignored if not actively used but custom implementations of {@link MutableState}.
     *
     * @return The used mutation policy.
     */
    @NotNull
    StateMutationPolicy<T> stateMutationPolicy();
}
