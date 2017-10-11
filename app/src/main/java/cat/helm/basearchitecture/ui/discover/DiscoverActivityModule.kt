package cat.helm.basearchitecture.ui.discover

import dagger.Module
import dagger.Provides

/**
 * Created by Héctor on 09/10/2017.
 */
@Module
class DiscoverActivityModule {
    @Provides
    internal fun provideDiscoverView(discoverActivity: DiscoverActivity): DiscoverView = discoverActivity
}
