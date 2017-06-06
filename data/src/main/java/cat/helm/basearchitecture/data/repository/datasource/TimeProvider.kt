package cat.helm.ureentool.data.repository.datasource

/**
 * Created by Borja on 30/3/17.
 */
interface TimeProvider {
    fun currentTimeMillis(): Long

}