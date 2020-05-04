package fr.benchaabane.domainlayer.articles

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.junit.Test

class RetrieveArticlesUseCaseTest {

    @Test
    fun `execute`() {
        // Given
        val data = ArticlesData()
        val repository = mock<ArticlesRepository>{
            given(it.fetchArticles()).willReturn(Single.just(data.articles))
        }
        val useCase = RetrieveArticlesUseCase(repository)
        val expected = data.articles
        // When
        val testObserver = useCase.execute().test()
        // Then
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue(expected)
    }
}