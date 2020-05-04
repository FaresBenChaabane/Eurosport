package fr.benchaabane.presentationlayer.tools

import fr.benchaabane.commons.android.tools.TSchedulers
import fr.benchaabane.commons.tools.Logger
import fr.benchaabane.presentationlayer.ui.*
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.zipWith


// Rx

fun <T> Observable<T>.mapToStateWithRetry(retry: Observable<*>): Observable<State<T>> = map { item -> item.toState() }
        .startWith(loading())
        .doOnError { throwable -> Logger.e(throwable) }
        .onErrorResumeNext { throwable: Throwable ->
            Observable.just<State<T>>(throwable.toState())
                    .concatWith(Observable.error(throwable))
        }
        .retryWhen { error -> error.zipWith(retry) { error: Throwable, _ -> error } }

fun <T> Observable<T>.subscribeAsyncToStateWithRetry(retry: Observable<*>,
                                                     onSuccess: (T) -> Unit = {},
                                                     onError: (Throwable) -> Unit = {},
                                                     onLoading: () -> Unit = {}): Disposable = mapToStateWithRetry(retry)
        .subscribeAsync(onSuccess, onError, onLoading)


fun <T> Observable<State<T>>.subscribeAsync(onSuccess: (T) -> Unit = {},
                                            onError: (Throwable) -> Unit = {},
                                            onLoading: () -> Unit = {}): Disposable = subscribeOn(TSchedulers.io)
        .observeOn(TSchedulers.ui)
        .subscribe({ state ->
                       when {
                           state.isLoading -> onLoading()
                           state.isSuccess -> onSuccess(state.asSuccess().data)
                           state.isError -> onError(state.asError().throwable)
                       }
                   },
                   { onError(it) })
