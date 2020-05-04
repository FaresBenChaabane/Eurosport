package fr.benchaabane.presentationlayer.ui.articles.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.benchaabane.domainlayer.articles.GetStoryDetailsUseCase
import fr.benchaabane.presentationlayer.tools.subscribeAsyncToStateWithRetry
import fr.benchaabane.presentationlayer.ui.Resources
import fr.benchaabane.presentationlayer.ui.articles.StoryArticleUi
import fr.benchaabane.presentationlayer.ui.articles.toUi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.PublishSubject

class StoryDetailsViewModel(private val useCase: GetStoryDetailsUseCase,
                            private val resources: Resources)  : ViewModel(), IStoryDetailsViewModel {

    private val storyDetails = MutableLiveData<StoryArticleUi>()
    private val errorState = MutableLiveData<Boolean>()
    private val loadingState = MutableLiveData<Boolean>()

    private val retry = PublishSubject.create<Unit>()
    private val disposable = CompositeDisposable()

    override fun observeStoryDetails(): LiveData<StoryArticleUi> = storyDetails
    override fun observeError(): LiveData<Boolean> = errorState
    override fun observeLoading(): LiveData<Boolean> = loadingState
    override fun retry() = retry.onNext(Unit)
    override fun fetchStoryDetails(storyId: Int){
        disposable += useCase.execute(storyId = storyId)
            .map { it.toUi(resources) }
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
                    storyDetails.postValue(it)
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}