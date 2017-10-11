package cat.helm.basearchitecture.data.repository.query

import cat.helm.basearchitecture.Result

/**
 * Created by Borja on 21/3/17.
 */
interface Query {

    fun queryAll(parameters: HashMap<String, *>? = null, queryable: Any? = null): Result<Collection<*>, *> =
            Result.Failure()
    fun query(parameters: HashMap<String, *>? = null, queryable: Any? = null): Result<*, *> = Result.Failure()

}
