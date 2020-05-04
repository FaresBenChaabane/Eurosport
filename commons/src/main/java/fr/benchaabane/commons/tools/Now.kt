package fr.benchaabane.commons.tools

import org.jetbrains.annotations.TestOnly
import org.threeten.bp.Clock

object Now {
    private var clock: Clock? = null

    @TestOnly
    fun useClock(clock: Clock) {
        Now.clock = clock
    }

    @TestOnly
    fun resetClock() {
        clock = null
    }
}