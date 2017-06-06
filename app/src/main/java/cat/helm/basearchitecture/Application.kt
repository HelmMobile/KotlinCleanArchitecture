package cat.helm.basearchitecture

import android.app.Application
import cat.helm.basearchitecture.dependencyinjection.application.ApplicationComponent
import cat.helm.basearchitecture.dependencyinjection.application.ApplicationModule
import cat.helm.basearchitecture.dependencyinjection.application.DaggerApplicationComponent

/**
 * Created by Borja on 21/12/16.
 */
class Application : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}