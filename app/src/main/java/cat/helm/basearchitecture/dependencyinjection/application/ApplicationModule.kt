package cat.helm.basearchitecture.dependencyinjection.application

import android.content.Context
import cat.helm.basearchitecture.UiThread
import cat.helm.basearchitecture.async.PostExecutionThread
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Borja on 21/12/16.
 */
@Module
class ApplicationModule(val application: Context) {

    companion object{
        const val APPLICATION_CONTEXT = "appContext"
    }

    @Provides
    @Singleton
    @Named("APPLICATION_CONTEXT")
    fun provideApplicationContext() = application

    @Provides
    @Singleton
    fun providesPostExecutionThread() : PostExecutionThread = UiThread()

}