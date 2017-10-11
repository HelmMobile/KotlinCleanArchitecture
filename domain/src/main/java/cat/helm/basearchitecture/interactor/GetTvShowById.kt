package cat.helm.basearchitecture.interactor

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.repository.TvShowRepository
import javax.inject.Inject

/**
 * Created by Borja on 6/6/17.
 */
class GetTvShowById @Inject constructor(private val tvShowRepository: TvShowRepository)
    : Interactor<TvShow, GetTvShowById.Parameters>() {

    override fun run(params: Parameters): Result<TvShow, *> = tvShowRepository.getTvShowById(params.id)

    data class Parameters(val id: Int)
}

