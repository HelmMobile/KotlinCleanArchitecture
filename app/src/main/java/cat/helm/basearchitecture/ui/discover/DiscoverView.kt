package cat.helm.basearchitecture.ui.discover

import cat.helm.basearchitecture.model.TvShow

/**
 * Created by Borja on 1/6/17.
 */
interface DiscoverView {
    fun displayTvShow(it: List<TvShow>)
    fun navigateToDetailActivity(id: Int)
}