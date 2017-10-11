package cat.helm.basearchitecture.interactor

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.repository.TvShowRepository
import javax.inject.Inject

/**
 * Created by Borja on 6/6/17.
 */

class GetTvShowByName @Inject constructor(private val tvShowRepository: TvShowRepository)
    : Interactor<List<TvShow>, GetTvShowByName.Parameters>() {

    override fun run(params: Parameters): Result<List<TvShow>, *> = tvShowRepository.getTvShowByName(params.name)

    data class Parameters (val name: String)
}

