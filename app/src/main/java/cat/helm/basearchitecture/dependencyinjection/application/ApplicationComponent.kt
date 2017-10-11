package cat.helm.basearchitecture.dependencyinjection.application

import android.app.Application
import cat.helm.basearchitecture.BaseApplication
import cat.helm.basearchitecture.data.dependencyinjection.DataModule
import cat.helm.basearchitecture.dependencyinjection.activity.ActivityInjector
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by Borja on 21/12/16.
 */
@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        AndroidInjectionModule::class,
        ActivityInjector::class,
        DataModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}
