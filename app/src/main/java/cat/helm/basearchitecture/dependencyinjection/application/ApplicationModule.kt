package cat.helm.basearchitecture.dependencyinjection.application

import android.content.Context
import cat.helm.basearchitecture.UiThread
import cat.helm.basearchitecture.async.PostExecutionThread
import cat.helm.basearchitecture.data.dependencyinjection.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Borja on 21/12/16.
 */
@Module
class ApplicationModule(val application: Context) {



    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplicationContext() = application

    @Provides
    @Singleton
    fun providesPostExecutionThread() : PostExecutionThread = UiThread()

}