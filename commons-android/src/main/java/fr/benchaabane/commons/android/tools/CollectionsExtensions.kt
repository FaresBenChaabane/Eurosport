package fr.benchaabane.commons.android.tools

import android.util.SparseArray


// SparseArray
inline fun <T, V> Iterable<T>.toSparseArray(keySelector: (T) -> Int, transform: (T) -> V): SparseArray<V> {
    return toSparseArray(SparseArray(10), keySelector, transform)
}

inline fun <T, V> Iterable<T>.toSparseArray(destination: SparseArray<V>, keySelector: (T) -> Int, transform: (T) -> V): SparseArray<V> {
    for (element in this) {
        destination.put(keySelector(element), transform(element))
    }
    return destination
}

