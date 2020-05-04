package fr.benchaabane.presentationlayer.ui.articles.listing

import androidx.lifecycle.Observer
import com.jakewharton.threetenabp.AndroidThreeTen
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import fr.benchaabane.commons.android.tools.TSchedulers
import fr.benchaabane.commons.android.tools.initForTest
import fr.benchaabane.commons.tools.Now
import fr.benchaabane.domainlayer.articles.RetrieveArticlesUseCase
import fr.benchaabane.presentationlayer.tools.views.MixedListAdapter
import fr.benchaabane.presentationlayer.ui.AndroidResources
import fr.benchaabane.presentationlayer.ui.Resources
import fr.benchaabane.presentationlayer.ui.articles.ArticlesData
import fr.benchaabane.presentationlayer.ui.articles.NOW_CLOCK
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(qualifiers = "fr")
class ArticlesListingViewModelTest {

    private lateinit var viewModel: IArticlesListingViewModel
    private lateinit var data: ArticlesData
    private lateinit var resources: Resources
    private lateinit var useCase: RetrieveArticlesUseCase
    private lateinit var loadingObserver: Observer<Boolean>
    private lateinit var errorObserver: Observer<Boolean>
    private lateinit var articlesListObserver: Observer<List<MixedListAdapter.Item>>

    @Before
    fun setUp(){
        AndroidThreeTen.init(RuntimeEnvironment.application)
        Now.useClock(NOW_CLOCK)
        TSchedulers.initForTest()
        data = ArticlesData()
        resources = AndroidResources(RuntimeEnvironment.application.resources)
        useCase = mock {
            given(it.execute()).willReturn(Observable.just(data.domain.articles))
        }
        loadingObserver = mock()
        errorObserver = mock()
        articlesListObserver = mock()
    }

    @After
    fun reset(){
        Now.resetClock()
    }

    @Test
    fun `fetch articles`(){
        // Given
        viewModel = ArticlesListingViewModel(useCase, resources)
        // When
        viewModel.observeError().observeForever(errorObserver)
        viewModel.observeLoading().observeForever(loadingObserver)
        viewModel.observeArticlesList().observeForever(articlesListObserver)
        // Then
        Assert.assertTrue(viewModel.observeLoading().hasObservers())
        Assert.assertTrue(viewModel.observeError().hasObservers())
        Assert.assertTrue(viewModel.observeArticlesList().hasObservers())
        verify(loadingObserver).onChanged(false)
        verify(errorObserver).onChanged(false)
        verify(articlesListObserver).onChanged(listOf(data.ui.articles.storiesUi.first(), data.ui.articles.videosUi.first()))
    }

    @Test
    fun `error fetching articles`(){
        // Given
        useCase = mock {
            given(it.execute()).willReturn(Observable.error(Exception()))
        }
        viewModel = ArticlesListingViewModel(useCase, resources)
        // When
        viewModel.observeError().observeForever(errorObserver)
        viewModel.observeLoading().observeForever(loadingObserver)
        viewModel.observeArticlesList().observeForever(articlesListObserver)
        // Then
        Assert.assertTrue(viewModel.observeLoading().hasObservers())
        Assert.assertTrue(viewModel.observeError().hasObservers())
        Assert.assertTrue(viewModel.observeArticlesList().hasObservers())
        verify(loadingObserver).onChanged(false)
        verify(errorObserver).onChanged(true)

    }
}