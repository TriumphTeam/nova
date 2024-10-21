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

import dev.triumphteam.nova.builtin.SimpleMutableState;
import dev.triumphteam.nova.policy.StateMutationPolicy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

/**
 * Abstract implementation for a {@link MutableState}.
 * The mutability of the value {@link T} is dependent on the given {@link StateMutationPolicy}.
 *
 * @param <T> The type of the value.
 * @see SimpleMutableState
 */
public abstract class AbstractMutableState<T> extends AbstractState implements MutableState<T> {

    private final StateMutationPolicy<T> mutationPolicy;
    private T value;

    public AbstractMutableState(final T value, final @NotNull StateMutationPolicy<T> mutationPolicy) {
        this.value = value;
        this.mutationPolicy = mutationPolicy;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(final T value) {
        // Will only mutate the value if the policy allows it
        if (!mutationPolicy.shouldMutate(this.value, value)) return;

        this.value = value;
        trigger();
    }

    @Override
    public T update(final @NotNull Function<T, T> update) {
        final T newValue = update.apply(value);
        set(newValue);
        return value;
    }

    @Override
    public @NotNull StateMutationPolicy<T> stateMutationPolicy() {
        return mutationPolicy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractMutableState<?> that = (AbstractMutableState<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "MutableState{" + "value=" + value + ",mutationPolicy=" + mutationPolicy + "}";
    }
}
