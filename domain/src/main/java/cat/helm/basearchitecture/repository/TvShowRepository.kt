package cat.helm.basearchitecture.repository

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.model.TvShow

/**
 * Created by Borja on 1/6/17.
 */
interface TvShowRepository {
    fun getAllPopularTvShows(): Result<List<TvShow>, *>
}