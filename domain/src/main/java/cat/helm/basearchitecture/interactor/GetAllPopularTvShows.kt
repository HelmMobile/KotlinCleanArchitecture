package cat.helm.basearchitecture.interactor

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.async.PostExecutionThread
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.repository.TvShowRepository
import javax.inject.Inject

/**
 * Created by Borja on 1/6/17.
 */
class GetAllPopularTvShows @Inject constructor(postExecutionThread: PostExecutionThread,
                                               val tvShowRepository: TvShowRepository)
    : Interactor<List<TvShow>, Unit>(postExecutionThread) {

    override fun run(params: Unit): Result<List<TvShow>, *> = tvShowRepository.getAllPopularTvShows()
}