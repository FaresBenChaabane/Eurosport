package fr.benchaabane.commons.tools

import kotlin.math.min

fun <T, V> List<T>.mixAndAlternateWith(otherList: List<V>): List<Any> {
    var mixedList: List<Any> = emptyList()
    val min = min(this.size, otherList.size)
    for (i: Int in 0 until min) {
        mixedList = mixedList.plus(this[i] as Any).plus(otherList[i] as Any)
    }
    if (this.size > min) mixedList = mixedList.plus(this.subList(min, size) as List<Any>)
    if (otherList.size > min) mixedList = mixedList.plus(otherList.subList(min, size) as List<Any>)

    return mixedList
}
