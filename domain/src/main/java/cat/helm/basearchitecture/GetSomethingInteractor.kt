package cat.helm.basearchitecture

import cat.helm.basearchitecture.async.PostExecutionThread
import cat.helm.basearchitecture.async.doAsync
import cat.helm.basearchitecture.async.onComplete
import cat.helm.basearchitecture.repository.SomethingRepository
import javax.inject.Inject

/**
 * Created by Borja on 2/1/17.
 */
class GetSomethingInteractor @Inject constructor(val postExecutionThread: PostExecutionThread,
                                                 val repository: SomethingRepository) {

    fun execute(delegate: (result: Result<String, Exception>) -> Unit) = doAsync {

        val result = repository.getSomething()

        onComplete(postExecutionThread) {
            delegate.invoke(result)
        }

    }

}