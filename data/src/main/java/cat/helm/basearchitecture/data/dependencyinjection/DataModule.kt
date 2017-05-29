package cat.helm.basearchitecture.data.dependencyinjection

import cat.helm.basearchitecture.data.repository.query.Query
import cat.helm.basearchitecture.data.repository.something.SomethingDataRepository
import cat.helm.basearchitecture.data.repository.something.SomethingDataSource
import cat.helm.basearchitecture.data.repository.something.query.QuerySomethingFake
import cat.helm.basearchitecture.repository.SomethingRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton

/**
 * Created by Borja on 4/1/17.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun providesGetSomethingRepository(dataSource: SomethingDataSource) : SomethingRepository = SomethingDataRepository(dataSource)


    @Provides
    @Singleton
    @IntoSet
    fun providesQuerySomething(query: QuerySomethingFake): QuerySomethingFake{
        return query
    }

    @Provides
    @Singleton
    fun providesSomethingDataRepository(query: QuerySomethingFake): SomethingDataSource{
        val queries = HashSet<Query>()
        queries.add(query)
        return SomethingDataSource(queries)
    }
}
