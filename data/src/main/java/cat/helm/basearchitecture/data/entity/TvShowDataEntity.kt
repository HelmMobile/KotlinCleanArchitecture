package cat.helm.basearchitecture.data.entity

import cat.helm.basearchitecture.data.repository.datasource.Identifiable

/**
 * Created by Borja on 1/6/17.
 */
data class TvShowDataEntity(val popularity: Float, val id: Int, val name: String, val backdrop_path: String, val overview: String) : Identifiable<Int> {
    override fun getKey(): Int = id

}