package cat.helm.basearchitecture.data

import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.repository.SomethingRepository

/**
 * Created by Borja on 4/1/17.
 */
class SomethingDataRepository: SomethingRepository {

    override fun getSomething(): Result {
        return Result.Success("hi")
    }
}