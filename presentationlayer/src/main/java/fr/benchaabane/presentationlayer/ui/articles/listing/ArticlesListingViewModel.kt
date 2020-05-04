package fr.benchaabane.presentationlayer.ui.articles.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.benchaabane.commons.tools.mixAndAlternateWith
import fr.benchaabane.domainlayer.articles.RetrieveArticlesUseCase
import fr.benchaabane.presentationlayer.tools.subscribeAsyncToStateWithRetry
import fr.benchaabane.presentationlayer.tools.views.MixedListAdapter
import fr.benchaabane.presentationlayer.ui.Resources
import fr.benchaabane.presentationlayer.ui.articles.toUi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject

class ArticlesListingViewModel(private val useCase: RetrieveArticlesUseCase,
                               private val resources: Resources) : ViewModel(), IArticlesListingViewModel {

    private val articlesList = MutableLiveData<List<MixedListAdapter.Item>>()
    private val errorState = MutableLiveData<Boolean>()
    private val loadingState = MutableLiveData<Boolean>()

    private val retry = PublishSubject.create<Unit>()

    override fun observeArticlesList(): LiveData<List<MixedListAdapter.Item>> = articlesList
    override fun observeError(): LiveData<Boolean> = errorState
    override fun observeLoading(): LiveData<Boolean> = loadingState
    override fun retry() = retry.onNext(Unit)

    private val disposable = CompositeDisposable()

    init {
        retrieveArticles()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    private fun retrieveArticles() {
        disposable += useCase.execute()
            .map { articles -> articles.toUi(resources)}
            .map { articlesUi -> articlesUi.storiesUi.mixAndAlternateWith(articlesUi.videosUi) as List<MixedListAdapter.Item>}
            .subscribeAsyncToStateWithRetry(
                retry = retry,
                onLoading = {
                    errorState.postValue(false)
                    loadingState.postValue(true)
                },
                onError = {
                    loadingState.postValue(false)
                    errorState.postValue( true)
                },
                onSuccess = {
                    loadingState.postValue(false)
                    errorState.postValue( false)
                    articlesList.postValue(it)
                }
            )
    }
}