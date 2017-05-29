package cat.helm.basearchitecture.repository

import cat.helm.basearchitecture.Result

/**
 * Created by Borja on 4/1/17.
 */
interface SomethingRepository {

    fun getSomething(): Result<String, Exception>
}