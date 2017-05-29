package cat.helm.basearchitecture.data.repository.something

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.data.repository.something.query.QuerySomething
import cat.helm.basearchitecture.repository.SomethingRepository
import cat.helm.ureentool.data.repository.Repository
import javax.inject.Inject

/**
 * Created by Borja on 4/1/17.
 */
class SomethingDataRepository : Repository<String, String>, SomethingRepository {

    @Inject constructor(dataSource: SomethingDataSource) : super() {
        readableDataSources.add(dataSource)
    }

    override fun getSomething(): Result<String, Exception> {

        return query(QuerySomething::class.java)

    }

}