package fr.benchaabane.datalayer.articles

import com.nhaarman.mockitokotlin2.mock
import fr.benchaabane.domainlayer.articles.ArticlesRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class ArticlesRepositoryTest {

    private lateinit var apiService: ArticlesApiService
    private lateinit var data: ArticlesData
    private lateinit var repository: ArticlesRepository

    @Before
    fun setUp(){
        apiService = mock()
        data = ArticlesData()
        repository = ArticlesRepositoryImp(apiService)
    }


    @Test
    fun `get articles`(){
        // Given
        `when`(apiService.getArticles()).thenAnswer { Single.just(data.json.articles) }
        // When
        val observer = repository.fetchArticles().test()
        // Then
        observer.assertComplete()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue(data.domain.articles)
    }
}