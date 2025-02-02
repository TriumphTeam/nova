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

import dev.triumphteam.nova.builtin.ListBackedListState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

/**
 * A {@link List} representation of a state.
 * An update is triggered when the list elements change.
 *
 * @param <T> The type of the elements of the list.
 */
public interface ListState<T> extends State, List<T>, RandomAccess {

    /**
     * Creates a new {@link ListState} with a backing {@link ArrayList}.
     *
     * @param <T> The type of the elements of the list.
     * @return A new {@link ListState}.
     */
    static <T> @NotNull ListState<T> of() {
        return of(new ArrayList<>());
    }

    /**
     * Creates a new {@link ListState} from the given elements.
     *
     * @param elements The elements to create a new list from.
     * @param <T>      The type of the elements of the list.
     * @return A new {@link ListState}.
     */
    @SafeVarargs
    static <T> @NotNull ListState<T> of(final T... elements) {
        return of(List.of(elements));
    }

    /**
     * Creates a new {@link ListState} with the given list as backing.
     *
     * @param backing The backing {@link List} for the state to use.
     * @param <T>     The type of the elements of the list.
     * @return A new {@link ListState}.
     */
    static <T> @NotNull ListState<T> of(final @NotNull List<T> backing) {
        return new ListBackedListState<>(backing);
    }
}
