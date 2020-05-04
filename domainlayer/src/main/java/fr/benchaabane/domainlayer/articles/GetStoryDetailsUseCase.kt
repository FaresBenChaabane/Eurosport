package fr.benchaabane.domainlayer.articles

import fr.benchaabane.commons.os.OpenWhenTesting
import io.reactivex.Observable

@OpenWhenTesting
class GetStoryDetailsUseCase(private val repository: ArticlesRepository){
    fun execute(storyId: Int): Observable<StoryArticle> = repository.fetchStoryDetails(storyId)
        .toObservable()
}