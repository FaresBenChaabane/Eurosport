package fr.benchaabane.presentationlayer.ui.articles.details

import androidx.lifecycle.LiveData
import fr.benchaabane.presentationlayer.ui.articles.StoryArticleUi

interface IStoryDetailsViewModel {
    fun observeStoryDetails(): LiveData<StoryArticleUi>
    fun observeError(): LiveData<Boolean>
    fun observeLoading(): LiveData<Boolean>
    fun retry()
    fun fetchStoryDetails(storyId: Int)
}