package cat.helm.basearchitecture.interactor

import cat.helm.basearchitecture.Result
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import kotlin.coroutines.experimental.AbstractCoroutineContextElement

/**
 * Created by Borja on 1/6/17.
 */
abstract class Interactor<out SuccessValue, in Parameters> {

    @Inject
    lateinit var continuation: AbstractCoroutineContextElement


    fun execute(parameters: Parameters,
                delegate: (result: Result<SuccessValue, *>) -> Unit) =
            launch(continuation) {
                val result = async {
                    run(parameters)
                }
                delegate(result.await())
            }


    abstract fun run(params: Parameters): Result<SuccessValue, *>
}
