package cat.helm.basearchitecture.ui.main

import cat.helm.basearchitecture.GetSomethingInteractor
import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.dependencyinjection.scope.PerActivity
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.newSingleThreadContext
import java.util.concurrent.CompletableFuture
import javax.inject.Inject
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by Borja on 21/12/16.
 */
@PerActivity
class MainPresenter
@Inject constructor(val view: MainView,
                    val getSomethingInteractor: GetSomethingInteractor) {

    fun changeTextButtonPressed() {



        async<String>(newSingleThreadContext(""), true, {

            return@async ""
        })


        getSomethingInteractor.execute {
            result ->
            when (result) {
                is Result.Success<String> -> view.changeText(result.result)
                is Result.Error -> view.changeText("error")
            }
        }
    }

    fun nextActivityButtonPressed() {
        view.navigateToSecondaryActivity()

    }

    suspend fun <T> CompletableFuture<T>.await(): T =
            suspendCoroutine<T> { cont: Continuation<T> ->
                whenComplete { result, exception ->
                    if (exception == null) // the future has been completed normally
                        cont.resume(result)
                    else // the future has completed with an exception
                        cont.resumeWithException(exception)
                }
            }
}