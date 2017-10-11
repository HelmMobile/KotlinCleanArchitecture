package cat.helm.basearchitecture.ui.detail

import dagger.Module
import dagger.Provides

/**
 * Created by Héctor on 09/10/2017.
 */
@Module
class DetailActivityModule {

    @Provides
    internal fun provideDetailView(detailActivity: DetailActivity): DetailView = detailActivity
}
