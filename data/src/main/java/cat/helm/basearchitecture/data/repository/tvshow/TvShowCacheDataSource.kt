package cat.helm.basearchitecture.data.repository.tvshow

import cat.helm.basearchitecture.data.entity.TvShowDataEntity
import cat.helm.basearchitecture.data.repository.query.Query
import cat.helm.ureentool.data.dependencyinjection.qualifier.queries.DefaultQueries
import cat.helm.ureentool.data.repository.datasource.InMemoryCacheDataSource
import cat.helm.ureentool.data.repository.datasource.TimeProvider
import javax.inject.Inject

/**
 * Created by Borja on 6/6/17.
 */
class TvShowCacheDataSource @Inject constructor(timeProvider: TimeProvider, ttlMillis: Long, @DefaultQueries override val queries: MutableSet<Query>) : InMemoryCacheDataSource<Int, TvShowDataEntity>(timeProvider, ttlMillis, queries)