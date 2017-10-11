package cat.helm.basearchitecture.ui.exception

import android.content.Context
import cat.helm.basearchitecture.R
import cat.helm.basearchitecture.data.dependencyinjection.qualifier.ApplicationContext
import cat.helm.basearchitecture.model.exceptions.NetworkException
import cat.helm.basearchitecture.ui.base.BaseView
import dagger.Reusable
import javax.inject.Inject

/**
 * Created by Borja on 23/5/17.
 */
@Reusable
class SimpleExceptionHandler @Inject constructor(@ApplicationContext val context: Context) : ExceptionHandler {

    override fun<T: BaseView> notifyException(view: T, exception: Exception?) {
        when (exception) {

            is NetworkException.UnauthorizedException -> view.showException("Unauthorized")
            is NetworkException.NoInternetConnection -> view.showException("No internet connection")
            is NetworkException.ServerException -> view.showException(context.getString(R.string.server_error))
            else -> view.showException(exception?.message ?: "Unknown error")

        }
    }
}
