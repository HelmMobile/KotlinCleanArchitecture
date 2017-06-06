package cat.helm.basearchitecture.interactor

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.async.PostExecutionThread
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.repository.TvShowRepository
import javax.inject.Inject

/**
 * Created by Borja on 6/6/17.
 */
class GetTvShowById @Inject constructor(postExecutionThread: PostExecutionThread, val tvShowRepository: TvShowRepository) : Interactor<TvShow, Parameters>(postExecutionThread) {

    override fun run(params: Parameters): Result<TvShow, *> = tvShowRepository.getTvShowById(params.id)

}

data class Parameters(val id: Int)

