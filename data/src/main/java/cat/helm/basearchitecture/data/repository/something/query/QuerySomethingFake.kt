package cat.helm.basearchitecture.data.repository.something.query

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.Result.Success
import javax.inject.Inject

/**
 * Created by Borja on 21/3/17.
 */
class QuerySomethingFake @Inject constructor() : QuerySomething {

    override fun queryAll(parameters: HashMap<String, *>?): Result<Collection<String>,*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(parameters: HashMap<String, *>?): Result<String,*> {
        return Success("Hello Cat")
    }


}