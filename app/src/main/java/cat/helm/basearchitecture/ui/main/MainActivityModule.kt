package cat.helm.basearchitecture.ui.main

import dagger.Module
import dagger.Provides

/**
 * Created by Borja on 17/7/17.
 */
@Module
class MainActivityModule {

    @Provides
    internal fun provideMainView(mainActivity: MainActivity): MainView {
        return mainActivity
    }
}
