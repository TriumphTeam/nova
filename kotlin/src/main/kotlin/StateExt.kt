package dev.triumphteam.nova

import dev.triumphteam.nova.policy.StateMutationPolicy
import kotlin.reflect.KProperty

public operator fun <T> MutableState<T>.getValue(thisRef: Any?, property: KProperty<*>): T = value

public operator fun <T> MutableState<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    setValue(value)
}

public operator fun <T> ListState<T>.getValue(thisRef: Any?, property: KProperty<*>): MutableList<T> = this

public fun <T : Any> mutableStateOf(
    value: T,
    mutationPolicy: StateMutationPolicy = StateMutationPolicy.StructuralEquality.get()
): MutableState<T> = MutableState.of(value, mutationPolicy)

public fun <T : Any?> nullableStateOf(
    value: T?,
    mutationPolicy: StateMutationPolicy = StateMutationPolicy.StructuralEquality.get()
): MutableState<T?> = MutableState.ofNullable(value, mutationPolicy)

public fun <T : Any> mutableStateListOf(backing: MutableList<T> = mutableListOf()): ListState<T> = ListState.of(backing)

public fun <T : Any> mutableStateListOf(vararg elements: T): ListState<T> = ListState.of(mutableListOf(*elements))
