package cat.helm.basearchitecture.data.repository.tvshow

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.Result.Failure
import cat.helm.basearchitecture.data.entity.TvShowDataEntity
import cat.helm.basearchitecture.data.network.TvShowService
import cat.helm.basearchitecture.data.network.parseResponse
import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.data.repository.query.Query
import cat.helm.basearchitecture.data.dependencyinjection.qualifier.DefaultQueries
import cat.helm.basearchitecture.data.network.ConnectionChecker
import cat.helm.basearchitecture.model.exceptions.NetworkException.NoInternetConnection
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Borja on 1/6/17.
 */
class TvShowApiDataSource @Inject constructor(@DefaultQueries override val queries: MutableSet<Query>,
                                              val retrofit: Retrofit,
                                              val connectionChecker: ConnectionChecker)
    : ReadableDataSource<Int, TvShowDataEntity> {

    override fun getAll(): Result<Collection<TvShowDataEntity>, Exception> {
        if (connectionChecker.thereIsConnectivity()) {
            val tvShowService = retrofit.create(TvShowService::class.java)
            val response = tvShowService.getPopularTvShows().execute()
            return response.parseResponse("results")
        }
        return Failure(NoInternetConnection())
    }

    override fun getByKey(key: Int): Result<TvShowDataEntity, *> {
        if (connectionChecker.thereIsConnectivity()) {
            val tvShowService = retrofit.create(TvShowService::class.java)
            val response = tvShowService.getTvShowById(key).execute()
            return response.parseResponse()
        }
        return Failure(NoInternetConnection())
    }
}