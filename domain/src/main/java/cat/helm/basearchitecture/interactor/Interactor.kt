package cat.helm.basearchitecture.interactor

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.async.PostExecutionThread
import cat.helm.basearchitecture.async.doAsync
import cat.helm.basearchitecture.async.onComplete

/**
 * Created by Borja on 1/6/17.
 */
abstract class Interactor<out SuccessValue, in Parameters > constructor(val postExecutionThread: PostExecutionThread) {

    fun execute(parameters: Parameters, delegate: (result: Result<SuccessValue, *>) -> Unit) = doAsync {
        val result = run(parameters)

        onComplete(postExecutionThread) {
            delegate(result)
        }
    }

    abstract fun run(params: Parameters): Result<SuccessValue, *>
}