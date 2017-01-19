package cat.helm.basearchitecture.data

import cat.helm.basearchitecture.repository.SomethingRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Borja on 4/1/17.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun providesGetSomethingRepository() : SomethingRepository = SomethingDataRepository()
}