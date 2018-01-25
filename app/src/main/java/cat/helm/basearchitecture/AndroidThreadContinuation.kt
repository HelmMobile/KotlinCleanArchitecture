package cat.helm.basearchitecture

import android.os.Handler
import android.os.Looper
import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.ContinuationInterceptor

/**
 * Created by borja on 25/01/2018.
 */
class AndroidThreadContinuation<T>(private val continuation: Continuation<T>): Continuation<T> by continuation {
    override fun resume(value: T) {
        if (Looper.myLooper() == Looper.getMainLooper()) continuation.resume(value)
        else Handler(Looper.getMainLooper()).post { continuation.resume(value) }
    }

    override fun resumeWithException(exception: Throwable) {
        if (Looper.myLooper() == Looper.getMainLooper()) continuation.resumeWithException(exception)
        else Handler(Looper.getMainLooper()).post { continuation.resumeWithException(exception) }
    }
}

object Android : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
            AndroidThreadContinuation(continuation)
}
