package cat.helm.basearchitecture.data.repository

import cat.helm.basearchitecture.data.repository.query.Query

/**
 * Created by Borja on 21/3/17.
 */

fun Query<*>.implements(kInterface: Class<*>): Boolean{
    return kInterface.isAssignableFrom(this::class.java)
}