package cat.helm.basearchitecture.ui.discover

import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.ui.base.BaseView

/**
 * Created by Borja on 1/6/17.
 */
interface DiscoverView: BaseView {
    fun displayTvShow(it: List<TvShow>)
    fun navigateToDetailActivity(id: Int)

}