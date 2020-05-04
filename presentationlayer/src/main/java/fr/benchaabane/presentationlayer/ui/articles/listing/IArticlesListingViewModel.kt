package fr.benchaabane.presentationlayer.ui.articles.listing

import androidx.lifecycle.LiveData
import fr.benchaabane.presentationlayer.tools.views.MixedListAdapter

interface IArticlesListingViewModel {
    fun observeArticlesList(): LiveData<List<MixedListAdapter.Item>>
    fun observeError(): LiveData<Boolean>
    fun observeLoading(): LiveData<Boolean>
    fun retry()
}