package cat.helm.basearchitecture.dependencyinjection.activity

import cat.helm.basearchitecture.ui.main.MainActivity
import cat.helm.basearchitecture.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Borja on 17/7/17.
 */

@Module
abstract class ActivityInjector {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun contributeMainActivityInjector(): MainActivity

}