package fr.benchaabane.commons.tools

import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be`
import org.junit.Test

class CollectionsExtensionsTest {

    @Test
    fun `mixing lists into generic list`() {
        // Given
        val list1 = listOf("a", "b", "b", "c")
        val list2 = listOf("1", "2", "3")
        // When
        val mixedList = list1.mixAndAlternateWith(list2)
        // Then
        mixedList.size `should not be` 0
        mixedList.size `should be` 7
        mixedList[0] `should be` "a"
        mixedList[1] `should be` "1"
        mixedList[2]  `should be` "b"
        mixedList[3]  `should be` "2"
    }
}