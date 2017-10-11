package cat.helm.basearchitecture.ui.detail

import cat.helm.basearchitecture.interactor.GetTvShowById
import cat.helm.basearchitecture.ui.exception.SimpleExceptionHandler
import javax.inject.Inject

/**
 * Created by Borja on 6/6/17.
 */
class DetailPresenter @Inject constructor(val view: DetailView,
                                          val getTvShowById: GetTvShowById,
                                          val exceptionHandler: SimpleExceptionHandler) {

    fun onStart(id: Int) {
        getTvShowById.execute(GetTvShowById.Parameters(id)) {
            result ->

            result.success {
                tvShow ->
                view.showTvShow(tvShow)
            }

            result.failure {
                exception ->

                exceptionHandler.notifyException(view, exception)
            }
        }
    }
}
