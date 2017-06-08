package cat.helm.ureentool.ui.base

import android.content.Context
import cat.helm.basearchitecture.R
import cat.helm.basearchitecture.data.dependencyinjection.qualifier.ActivityContext
import cat.helm.basearchitecture.dependencyinjection.scope.PerActivity
import cat.helm.basearchitecture.ui.base.BaseView
import cat.helm.ureentool.model.exceptions.NetworkException
import javax.inject.Inject

/**
 * Created by Borja on 23/5/17.
 */
@PerActivity
class SimpleExceptionHandler @Inject constructor(@ActivityContext val context: Context, val view: BaseView) : ExceptionHandler {

    override fun notifyException(exception: Exception?) {
        val message: String
        when (exception) {
            is NetworkException.UnauthorizedException -> message = context.getString(R.string.unauthorized_error)
            is NetworkException.NoInternetConnection -> message = context.getString(R.string.no_internet_exception)
            is NetworkException.ServerException -> message = context.getString(R.string.server_error)
            else -> message = "Unknown error"
        }

        view.showException(message)
    }
}