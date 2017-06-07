package cat.helm.basearchitecture.data.repository.tvshow.query

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.entity.TvShowDataEntity
import java.util.*
import javax.inject.Inject

/**
 * Created by Borja on 7/6/17.
 */
class GetTvShowByNameCacheQuery @Inject constructor() : GetTvShowByNameQuery {

    override fun queryAll(parameters: HashMap<String, *>?, queryable: Any?): Result<Collection<*>, *> {
        val cache = queryable as java.util.HashMap<String, TvShowDataEntity>
        val queryResult = ArrayList<TvShowDataEntity>()
        for ((_, key) in cache) {
            if (key.name.contains(parameters!![GetTvShowByNameQuery.Parameters.NAME_QUERY] as String, true)) {
                queryResult.add(key)
            }
        }
        return Result.of { queryResult }

    }
}