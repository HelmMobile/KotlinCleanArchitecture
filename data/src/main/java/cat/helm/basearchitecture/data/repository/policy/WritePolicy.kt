package cat.helm.basearchitecture.data.repository.policy

/**
 * Created by Borja on 5/4/17.
 */
enum class WritePolicy {
    WRITE_CACHE_ONLY,
    WRITE_ALL;

    fun writeCache(): Boolean{
        return this == WRITE_CACHE_ONLY
    }

}