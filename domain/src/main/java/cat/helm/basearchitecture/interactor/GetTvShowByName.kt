package cat.helm.basearchitecture.interactor

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.async.PostExecutionThread
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.repository.TvShowRepository
import javax.inject.Inject

/**
 * Created by Borja on 6/6/17.
 */

class GetTvShowByName @Inject constructor(postExecutionThread: PostExecutionThread, val tvShowRepository: TvShowRepository) : Interactor<List<TvShow>, GetTvShowByName.Parameters>(postExecutionThread) {

    override fun run(params: Parameters): Result<List<TvShow>, *> {
        return tvShowRepository.getTvShowByName(params.name)
    }

    data class Parameters (val name: String)
}

