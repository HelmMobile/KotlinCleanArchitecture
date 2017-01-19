package cat.helm.basearchitecture.dependencyinjection.application

import android.app.Application
import cat.helm.basearchitecture.data.dependencyinjection.DataModule
import cat.helm.basearchitecture.dependencyinjection.activity.ActivityComponent
import cat.helm.basearchitecture.dependencyinjection.activity.ActivityModule
import cat.helm.basearchitecture.dependencyinjection.activity.ViewModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Borja on 21/12/16.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface ApplicationComponent {


    fun inject( application: Application)


    fun plus(activityModule: ActivityModule, viewModule: ViewModule) : ActivityComponent
}