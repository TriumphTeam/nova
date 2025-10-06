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
package dev.triumphteam.nova.policy;

import dev.triumphteam.nova.MutableState;
import org.jetbrains.annotations.Nullable;

/**
 * States how a {@link MutableState} handles equality.
 * By default, {@link StructuralEquality} is used.
 */
public interface StateMutationPolicy<T> {

    /**
     * Creates a {@link StateMutationPolicy} that checks for reference equality.
     *
     * @param <T> The type of the value the state will handle.
     * @return A new {@link StateMutationPolicy} instance using reference equality.
     */
    static <T> StateMutationPolicy<T> reference() {
        return new ReferenceEquality<>();
    }

    /**
     * Creates a {@link StateMutationPolicy} that checks for structural equality.
     * Structural equality means that two objects are considered equal if their
     * content is logically equivalent, typically determined via the {@link Object#equals(Object)} method.
     *
     * @param <T> The type of the value the state will handle.
     * @return A new {@link StateMutationPolicy} instance using structural equality.
     */
    static <T> StateMutationPolicy<T> structural() {
        return new StructuralEquality<>();
    }

    /**
     * Creates a {@link StateMutationPolicy} where values are never considered equal.
     * This policy ensures that all value assignments will trigger a mutation,
     * regardless of their content or reference.
     *
     * @param <T> The type of the value the state will handle.
     * @return A new {@link StateMutationPolicy} instance where no two values are ever treated as equivalent.
     */
    static <T> StateMutationPolicy<T> neverEqual() {
        return new NeverEqual<>();
    }

    /**
     * Checks if the value should be mutated by comparing the current and the new value.
     *
     * @param currentValue The first value.
     * @param newValue The second value.
     * @return Whether or not they are equivalent.
     */
    boolean shouldMutate(final @Nullable T currentValue, final @Nullable T newValue);

    /**
     * A {@link StateMutationPolicy} that checks for reference equality.
     */
    final class ReferenceEquality<T> implements StateMutationPolicy<T> {

        @Override
        public boolean shouldMutate(final @Nullable T currentValue, final @Nullable T newValue) {
            return currentValue != newValue;
        }

        @Override
        public String toString() {
            return "ReferenceEquality";
        }
    }

    /**
     * A {@link StateMutationPolicy} that checks for structural equality.
     */
    final class StructuralEquality<T> implements StateMutationPolicy<T> {

        @Override
        public boolean shouldMutate(final @Nullable T currentValue, final @Nullable T newValue) {
            if (currentValue == null || newValue == null) return false;
            return !currentValue.equals(newValue);
        }

        @Override
        public String toString() {
            return "StructuralEquality";
        }
    }

    /**
     * A {@link StateMutationPolicy} that makes values never be equivalent.
     */
    final class NeverEqual<T> implements StateMutationPolicy<T> {

        @Override
        public boolean shouldMutate(final @Nullable T currentValue, final @Nullable T newValue) {
            return true;
        }

        @Override
        public String toString() {
            return "NeverEqual";
        }
    }
}
