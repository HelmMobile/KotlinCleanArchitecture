package cat.helm.basearchitecture.data.repository.tvshow.query

import cat.helm.basearchitecture.data.repository.query.Query

/**
 * Created by Borja on 6/6/17.
 */
interface GetTvShowByNameQuery : Query {
    companion object Parameters {
        val NAME_QUERY: String = "name"
    }
}
