package fr.benchaabane.presentationlayer.ui.articles

import com.jakewharton.threetenabp.AndroidThreeTen
import fr.benchaabane.commons.android.tools.TSchedulers
import fr.benchaabane.commons.android.tools.initForTest
import fr.benchaabane.commons.tools.Now
import fr.benchaabane.presentationlayer.ui.AndroidResources
import fr.benchaabane.presentationlayer.ui.Resources
import org.amshove.kluent.`should be equal to`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(qualifiers = "fr")
class MappingTest {

    private lateinit var data: ArticlesData
    private lateinit var resources: Resources

    @Before
    fun setUp(){
        AndroidThreeTen.init(RuntimeEnvironment.application)
        Now.useClock(NOW_CLOCK)
        TSchedulers.initForTest()
        data = ArticlesData()
        resources = AndroidResources(RuntimeEnvironment.application.resources)
    }

    @After
    fun reset() {
        Now.resetClock()
    }

    @Test
    fun `map domain to ui`(){
        //Given
        val expected = data.ui.articles
        // When
        val ui = data.domain.articles.toUi(resources)
        // Then
        ui `should be equal to` expected
    }
}