package cat.helm.basearchitecture.dependencyinjection.activity

import cat.helm.basearchitecture.ui.base.BaseView
import cat.helm.basearchitecture.ui.main.MainView
import dagger.Module
import dagger.Provides

/**
 * Created by Borja on 21/12/16.
 */
@Module
class ViewModule(val view: BaseView) {


    @Provides fun providesMainView() = view as MainView
}