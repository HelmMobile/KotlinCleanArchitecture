package cat.helm.basearchitecture.async

import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * Created by Borja on 3/1/17.
 */


class AsyncContext<T>(val executionThread: WeakReference<T>)


fun <T> T.doAsync(
        exceptionHandler: ((Throwable) -> Unit)? = null,
        task: AsyncContext<T>.() -> Unit
): Future<Unit> {
    val context = AsyncContext(WeakReference(this))
    return BackgroundExecutor.submit {
        try {
            context.task()
        } catch (thr: Throwable) {
            exceptionHandler?.invoke(thr) ?: Unit
        }
    }
}

internal object BackgroundExecutor {
    var executor: ExecutorService =
            Executors.newScheduledThreadPool(2 * Runtime.getRuntime().availableProcessors())

    fun <T> submit(task: () -> T): Future<T> {
        return executor.submit(task)
    }

}

fun <T> T.doAsync(
        exceptionHandler: ((Throwable) -> Unit)? = null,
        executorService: ExecutorService,
        task: AsyncContext<T>.() -> Unit
): Future<Unit> {
    val context = AsyncContext(WeakReference(this))
    return executorService.submit<Unit> {
        try {
            context.task()
        } catch (thr: Throwable) {
            exceptionHandler?.invoke(thr)
        }
    }
}


fun <T> T.onComplete(
        executorService: PostExecutionThread,
        exceptionHandler: ((Throwable) -> Unit)? = null,
        task: AsyncContext<T>.() -> Unit
) {
    val context = AsyncContext(WeakReference(this))
    executorService.submit {
        try {
            context.task()
        } catch (thr: Throwable) {
            exceptionHandler?.invoke(thr)
        }
    }
}