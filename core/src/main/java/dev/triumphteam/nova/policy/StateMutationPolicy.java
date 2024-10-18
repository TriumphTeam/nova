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
package dev.triumphteam.nova.policy;

import dev.triumphteam.nova.MutableState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * States how a {@link MutableState} handles equality.
 * By default, {@link StructuralEquality} is used.
 */
public interface StateMutationPolicy {

    /**
     * Checks if the two values are equivalent.
     *
     * @param a The first value.
     * @param b The second value.
     * @return Whether or not they are equivalent.
     */
    boolean equivalent(final @Nullable Object a, final @Nullable Object b);

    /**
     * A {@link StateMutationPolicy} that checks for reference equality.
     * This class is a singleton and {@link ReferenceEquality#INSTANCE} should always be the used instance.
     */
    final class ReferenceEquality implements StateMutationPolicy {

        @NotNull
        private static final StateMutationPolicy INSTANCE = new ReferenceEquality();

        private ReferenceEquality() {}

        public static @NotNull StateMutationPolicy get() {
            return INSTANCE;
        }

        @Override
        public boolean equivalent(final @Nullable Object a, final @Nullable Object b) {
            return a == b;
        }

        @Override
        public String toString() {
            return "ReferenceEquality";
        }
    }

    /**
     * A {@link StateMutationPolicy} that checks for structural equality.
     * This class is a singleton and {@link StructuralEquality#INSTANCE} should always be the used instance.
     */
    final class StructuralEquality implements StateMutationPolicy {

        @NotNull
        private static final StateMutationPolicy INSTANCE = new StructuralEquality();

        private StructuralEquality() {}

        public static @NotNull StateMutationPolicy get() {
            return INSTANCE;
        }

        @Override
        public boolean equivalent(final @Nullable Object a, final @Nullable Object b) {
            if (a == null || b == null) return false;
            return a.equals(b);
        }

        @Override
        public String toString() {
            return "StructuralEquality";
        }
    }

    /**
     * A {@link StateMutationPolicy} that makes values never be equivalent.
     * This class is a singleton and {@link NeverEqual#INSTANCE} should always be the used instance.
     */
    final class NeverEqual implements StateMutationPolicy {

        @NotNull
        private static final StateMutationPolicy INSTANCE = new NeverEqual();

        private NeverEqual() {}

        public static @NotNull StateMutationPolicy get() {
            return INSTANCE;
        }

        @Override
        public boolean equivalent(final @Nullable Object a, final @Nullable Object b) {
            return false;
        }

        @Override
        public String toString() {
            return "NeverEqual";
        }
    }
}
