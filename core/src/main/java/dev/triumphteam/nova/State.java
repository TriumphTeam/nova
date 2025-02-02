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
package dev.triumphteam.nova;

import dev.triumphteam.nova.builtin.EmptyState;
import dev.triumphteam.nova.builtin.SimpleMutableState;
import org.jetbrains.annotations.NotNull;

/**
 * A representation of a "state".
 * A state doesn't necessarily need to hold any value.
 * Its sole purpose is to trigger updates.
 *
 * @see MutableState
 * @see AbstractMutableState
 * @see SimpleMutableState
 */
public interface State {

    /**
     * Creates an empty state.
     *
     * @return A {@link State} that contains no values.
     */
    static @NotNull State empty() {
        return new EmptyState();
    }

    /**
     * Trigger a component to re-render.
     */
    void trigger();

    /**
     * Adds a new listener to the state.
     * Avoid calling this method manually if you don't know what you are doing,
     * this is mostly done internally by the {@link Object}.
     * <p>
     * The listener is tied to the lifecycle of the {@link Object},
     * so avoid holding the view if it is no longer necessary.
     *
     * @param stateful The {@link Object} object which will be handling this state.
     * @param listener The listener to be called when a state is triggered.
     */
    void addListener(final @NotNull Object stateful, final @NotNull Runnable listener);
}
