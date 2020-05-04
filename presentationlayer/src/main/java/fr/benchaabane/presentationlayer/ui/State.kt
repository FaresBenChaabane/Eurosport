package fr.benchaabane.presentationlayer.ui

import fr.benchaabane.presentationlayer.ui.State.Error
import fr.benchaabane.presentationlayer.ui.State.Loading
import fr.benchaabane.presentationlayer.ui.State.Success

sealed class State<T>(val isLoading: Boolean = false,
                      val isSuccess: Boolean = false,
                      val isError: Boolean = false) {
    class Loading<T> : State<T>(isLoading = true)
    class Error<T>(val throwable: Throwable) : State<T>(isError = true)
    class Success<T>(val data: T) : State<T>(isSuccess = true)
}

// Factory helper methods

fun <T> state(data: T): State<T> = Success(data)
fun <T> T.toState(): State<T> = Success(this)

fun <T> state(throwable: Throwable): State<T> = Error(throwable)
fun <T> Throwable.toState(): State<T> = Error(this)

fun <T> loading(): State<T> = Loading()


// Cast helper methods

fun <T> State<T>.asSuccess() = this as Success
fun <T> State<T>.asError() = this as Error



