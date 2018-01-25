package cat.helm.basearchitecture.dependencyinjection.application

import android.app.Application
import android.content.Context
import cat.helm.basearchitecture.Android
import cat.helm.basearchitecture.data.dependencyinjection.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.coroutines.experimental.AbstractCoroutineContextElement

/**
 * Created by Borja on 21/12/16.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun providesContinuation(): AbstractCoroutineContextElement = Android

}
