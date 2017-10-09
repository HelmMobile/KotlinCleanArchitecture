package cat.helm.basearchitecture.ui.exception

/**
 * Created by Borja on 23/5/17.
 */
interface ExceptionHandler {
    fun notifyException(exception: Exception?)
}