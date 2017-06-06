package cat.helm.basearchitecture.dependencyinjection.activity

import cat.helm.basearchitecture.dependencyinjection.scope.PerActivity
import cat.helm.basearchitecture.ui.discover.DiscoverActivity
import dagger.Subcomponent

/**
 * Created by Borja on 21/12/16.
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class, ViewModule::class))
interface ActivityComponent {

    fun inject(discoverActivity: DiscoverActivity)

}