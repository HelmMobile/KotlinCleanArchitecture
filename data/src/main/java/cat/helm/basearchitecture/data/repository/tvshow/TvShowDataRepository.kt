package cat.helm.basearchitecture.data.repository.tvshow

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.entity.TvShowDataEntity
import cat.helm.basearchitecture.data.entity.mapToTvShow
import cat.helm.basearchitecture.data.repository.datasource.CacheDataSource
import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.data.repository.tvshow.query.GetTvShowByNameQuery
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.repository.TvShowRepository
import cat.helm.ureentool.data.repository.Repository
import javax.inject.Inject

/**
 * Created by Borja on 1/6/17.
 */
class TvShowDataRepository @Inject constructor(tvShowApiDataSource: ReadableDataSource<Int, TvShowDataEntity>,
                                               tvShowCacheDataSource: CacheDataSource<Int, TvShowDataEntity>)
    : Repository<Int, TvShowDataEntity>(), TvShowRepository {

    init {
        cacheDataSources.add(tvShowCacheDataSource)
        readableDataSources.add(tvShowApiDataSource)
    }

    override fun getAllPopularTvShows(): Result<List<TvShow>, *> = getAll().map { it.map(TvShowDataEntity::mapToTvShow) }

    override fun getTvShowById(id: Int): Result<TvShow, *> = getByKey(id).map(TvShowDataEntity::mapToTvShow)

    override fun getTvShowByName(name: String): Result<List<TvShow>, *> {
        val parameters = HashMap<String, String>()
        parameters.put(GetTvShowByNameQuery.Parameters.NAME_QUERY, name)
        return queryAll(GetTvShowByNameQuery::class.java, parameters).map { it.map(TvShowDataEntity::mapToTvShow) }
    }
}