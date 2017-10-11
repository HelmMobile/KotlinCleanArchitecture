package cat.helm.basearchitecture.dependencyinjection.activity

import cat.helm.basearchitecture.dependencyinjection.scope.PerActivity
import cat.helm.basearchitecture.ui.detail.DetailActivity
import cat.helm.basearchitecture.ui.detail.DetailActivityModule
import cat.helm.basearchitecture.ui.discover.DiscoverActivity
import cat.helm.basearchitecture.ui.discover.DiscoverActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Héctor on 06/10/2017.
 */
@Module
abstract class ActivityInjector {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(DetailActivityModule::class))
    abstract fun contributeDetailActivityInjector(): DetailActivity

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(DiscoverActivityModule::class))
    abstract fun contributeDiscoverActivityInjector(): DiscoverActivity

}
