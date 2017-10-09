package cat.helm.basearchitecture.ui.detail

import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.ui.base.BaseView

/**
 * Created by Borja on 6/6/17.
 */
interface DetailView: BaseView {
    fun showTvShow(tvShow: TvShow)
}