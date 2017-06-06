package cat.helm.basearchitecture.data.repository.tvshow

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.entity.TvShowDataEntity
import cat.helm.basearchitecture.data.entity.mapToTvShow
import cat.helm.basearchitecture.data.repository.datasource.ReadableDataSource
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.repository.TvShowRepository
import cat.helm.ureentool.data.repository.Repository
import javax.inject.Inject

/**
 * Created by Borja on 1/6/17.
 */
class TvShowDataRepository @Inject constructor(tvShowApiDataSource: ReadableDataSource<Int, TvShowDataEntity>) : Repository<Int, TvShowDataEntity>(), TvShowRepository {

    init {
        readableDataSources.add(tvShowApiDataSource)
    }

    override fun getAllPopularTvShows(): Result<List<TvShow>, *> = getAll().map { it.map { it.mapToTvShow() } }
}