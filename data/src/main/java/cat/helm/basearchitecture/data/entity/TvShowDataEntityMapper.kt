package cat.helm.basearchitecture.data.entity

import cat.helm.basearchitecture.model.TvShow

/**
 * Created by Borja on 1/6/17.
 */

fun TvShowDataEntity.mapToTvShow() = TvShow(this.popularity, this.id, this.name, this.backdrop_path)