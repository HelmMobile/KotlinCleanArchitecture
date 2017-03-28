package cat.helm.basearchitecture.data.repository.something

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.repository.Repository
import cat.helm.basearchitecture.data.repository.something.query.QuerySomething
import cat.helm.basearchitecture.repository.SomethingRepository
import javax.inject.Inject

/**
 * Created by Borja on 4/1/17.
 */
class SomethingDataRepository : Repository<String, String>, SomethingRepository {

    @Inject constructor(dataSource: SomethingDataSource): super(){
        readableDataSources.add(dataSource)
    }
    override fun getSomething(): Result<String> {

        val query = query(QuerySomething::class.java)
        query?.let {
            return Result.Success(query)
        }
        return Result.Error()
    }

}