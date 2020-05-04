package fr.benchaabane.datalayer.articles

import org.amshove.kluent.`should be equal to`
import org.junit.Before
import org.junit.Test

class MappingTest {

    private lateinit var data: ArticlesData

    @Before
    fun setUp(){
        data = ArticlesData()
    }

    @Test
    fun `map data to domain`() {
        // Given
        val expected = data.domain.articles
        // When
        val domain = data.json.articles.toArticles()
        // Then
        domain `should be equal to` expected
    }
}