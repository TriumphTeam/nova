package dev.triumphteam.nova

import dev.triumphteam.nova.builtin.SimpleMutableState
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
    mutationPolicy: StateMutationPolicy = StateMutationPolicy.StructuralEquality.get()
): MutableState<T> = SimpleMutableState(value, mutationPolicy)

/** Creates an empty state. */
public fun emptyState(): State = State.empty()

/**
 * Creates a new list state from the given [backing] [MutableList].
 * Or an empty [backing] [MutableList] if none is given.
 */
public fun <T : Any> mutableStateListOf(backing: MutableList<T> = LinkedList()): ListState<T> = ListState.of(backing)

/** Create a new list state from the given elements. */
public fun <T : Any> mutableStateListOf(vararg elements: T): ListState<T> = ListState.of(mutableListOf(*elements))
