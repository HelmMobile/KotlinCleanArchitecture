package cat.helm.basearchitecture.ui.exception

import cat.helm.basearchitecture.ui.base.BaseView

/**
 * Created by Borja on 23/5/17.
 */
interface ExceptionHandler {
    fun <T : BaseView> notifyException(view: T, exception: Exception?)
}
