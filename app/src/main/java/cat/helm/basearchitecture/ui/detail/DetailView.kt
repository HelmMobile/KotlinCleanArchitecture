package cat.helm.basearchitecture.ui.detail

import cat.helm.basearchitecture.model.TvShow

/**
 * Created by Borja on 6/6/17.
 */
interface DetailView {
    fun showTvShow(tvShow: TvShow)
}