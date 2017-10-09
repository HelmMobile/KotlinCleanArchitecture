package cat.helm.basearchitecture.data.repository.tvshow

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.dependencyinjection.qualifier.queries.TvShowCacheQueries
import cat.helm.basearchitecture.data.entity.TvShowDataEntity
import cat.helm.basearchitecture.data.repository.datasource.InMemoryCacheDataSource
import cat.helm.basearchitecture.data.repository.datasource.TimeProvider
import cat.helm.basearchitecture.data.repository.implements
import cat.helm.basearchitecture.data.repository.query.Query
import javax.inject.Inject

/**
 * Created by Borja on 6/6/17.
 */
class TvShowCacheDataSource @Inject constructor(timeProvider: TimeProvider, ttlMillis: Long, @TvShowCacheQueries override val queries: MutableSet<Query>)
    : InMemoryCacheDataSource<Int, TvShowDataEntity>(timeProvider, ttlMillis, queries) {

    override fun queryAll(query: Class<*>, parameters: HashMap<String, *>?): Result<Collection<TvShowDataEntity>, *> {
        queries.forEach {
            possibleQuery ->
            if (possibleQuery.implements(query)) {
                return possibleQuery.queryAll(parameters, items) as Result<Collection<TvShowDataEntity>, *>
            }
        }
        return Result.Failure()
    }
}