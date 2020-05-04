package fr.benchaabane.domainlayer.articles

import fr.benchaabane.commons.os.OpenWhenTesting
import io.reactivex.Observable

@OpenWhenTesting
class RetrieveArticlesUseCase (private val repository: ArticlesRepository) {
    fun execute(): Observable<Articles> = repository.fetchArticles().toObservable()
}