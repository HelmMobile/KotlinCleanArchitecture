package cat.helm.basearchitecture.data.repository.tvshow

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.entity.TvShowDataEntity
import cat.helm.basearchitecture.data.network.TvShowService
import cat.helm.basearchitecture.data.network.parseResponse
import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.data.repository.query.Query
import cat.helm.ureentool.data.dependencyinjection.qualifier.queries.DefaultQueries
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Borja on 1/6/17.
 */
class TvShowApiDataSource @Inject constructor(@DefaultQueries override val queries: MutableSet<Query>,
                                              val retrofit: Retrofit)
    : ReadableDataSource<Int, TvShowDataEntity> {

    override fun getAll(): Result<Collection<TvShowDataEntity>, Exception> {
        val tvShowService = retrofit.create(TvShowService::class.java)
        val response = tvShowService.getPopularTvShows().execute()
        return response.parseResponse("results")

    }
}