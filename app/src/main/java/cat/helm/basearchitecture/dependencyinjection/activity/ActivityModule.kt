package cat.helm.basearchitecture.dependencyinjection.activity

import android.content.Context
import cat.helm.basearchitecture.data.dependencyinjection.qualifier.ActivityContext
import cat.helm.basearchitecture.dependencyinjection.scope.PerActivity
import cat.helm.ureentool.ui.base.ExceptionHandler
import cat.helm.ureentool.ui.base.SimpleExceptionHandler
import dagger.Module
import dagger.Provides

/**
 * Created by Borja on 21/12/16.
 */

@Module
class ActivityModule(val activity: Context) {

    @Provides
    @PerActivity
    @ActivityContext
    fun providesActivityContext() = activity

    @Provides
    @PerActivity
    fun providesExceptionHandler(exceptionHandler: SimpleExceptionHandler): ExceptionHandler = exceptionHandler
}