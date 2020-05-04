package fr.benchaabane.domainlayer.articles

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

class GetStoryDetailsUseCaseTest {

    @Test
    fun `execute`() {
        // Given
        val data = ArticlesData()
        val repository = mock<ArticlesRepository>{
            given(it.fetchStoryDetails(any())).willReturn(Single.just(data.articles.stories.first()))
        }
        val useCase = GetStoryDetailsUseCase(repository)
        val expected = data.articles.stories.first()
        // When
        val testObserver = useCase.execute(0).test()
        // Then
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue(expected)
    }
}