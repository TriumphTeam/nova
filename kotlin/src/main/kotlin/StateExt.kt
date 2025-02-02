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
package dev.triumphteam.nova

import dev.triumphteam.nova.builtin.SimpleMutableState
import dev.triumphteam.nova.holder.StateHolder
import dev.triumphteam.nova.policy.StateMutationPolicy
import java.util.LinkedList
import kotlin.reflect.KProperty

/** Allows you to use Kotlin's delegate feature to get the value of a mutable state. */
public operator fun <T> MutableState<T>.getValue(thisRef: Any?, property: KProperty<*>): T = get()

/** Allows you to use Kotlin's delegate feature to set the value of a mutable state. */
public operator fun <T> MutableState<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    set(value)
}

/** Allows you to use Kotlin's delegate feature to get the state as a [MutableList]. */
public operator fun <T> ListState<T>.getValue(thisRef: Any?, property: KProperty<*>): MutableList<T> = this

/** Creates a [MutableState] with a default value. */
public fun <T> mutableStateOf(
    value: T,
    mutationPolicy: StateMutationPolicy<T> = StateMutationPolicy.StructuralEquality()
): MutableState<T> = SimpleMutableState(value, mutationPolicy)

/** Creates an empty state. */
public fun emptyState(): State = State.empty()

/**
 * Creates a new list state from the given [backing] [MutableList].
 * Or an empty [backing] [MutableList] if none is given.
 */
public fun <T : Any> mutableListStateOf(backing: MutableList<T> = LinkedList()): ListState<T> = ListState.of(backing)

/** Create a new list state from the given elements. */
public fun <T : Any> mutableListStateOf(vararg elements: T): ListState<T> = ListState.of(mutableListOf(*elements))

/** Create a new map state from a backing map. */
public fun <K : Any, V : Any> mutableMapStateOf(backing: MutableMap<K, V> = mutableMapOf()): MapState<K, V> =
    MapState.of(backing)

/** Create a new map state from a collection of [Pair]s. */
public fun <K : Any, V : Any> mutableMapStateOf(vararg elements: Pair<K, V>): MapState<K, V> =
    MapState.of<K, V>().apply { putAll(elements) }

/** Make the [StateHolder] remember a [MapState] of key [K] and value [V] from the given values. */
public fun <K : Any, V : Any> StateHolder.rememberMap(vararg elements: Pair<K, V>): Map<K, V> {
    return rememberMap(mutableMapOf<K, V>().apply { putAll(elements) })
}
