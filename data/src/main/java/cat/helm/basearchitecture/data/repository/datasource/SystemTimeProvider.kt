package cat.helm.ureentool.data.repository.datasource

import javax.inject.Inject

/**
 * Created by Borja on 30/3/17.
 */
class SystemTimeProvider @Inject constructor() : TimeProvider {

    override fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}

