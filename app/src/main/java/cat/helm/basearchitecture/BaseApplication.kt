package cat.helm.basearchitecture

import android.app.Activity
import android.app.Application

import cat.helm.basearchitecture.dependencyinjection.application.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Borja on 21/12/16.
 */
class BaseApplication : Application(), HasActivityInjector {
    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
}
