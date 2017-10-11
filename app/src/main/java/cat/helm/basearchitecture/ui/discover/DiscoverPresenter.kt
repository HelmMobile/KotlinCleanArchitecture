package cat.helm.basearchitecture.ui.discover

import cat.helm.basearchitecture.dependencyinjection.scope.PerActivity
import cat.helm.basearchitecture.interactor.GetAllPopularTvShows
import cat.helm.basearchitecture.interactor.GetTvShowByName
import cat.helm.basearchitecture.ui.exception.SimpleExceptionHandler
import javax.inject.Inject

/**
 * Created by Borja on 1/6/17.
 */

@PerActivity
class DiscoverPresenter @Inject constructor(val view: DiscoverView,
                                            val getAllPopularTvShows: GetAllPopularTvShows,
                                            val getTvShowByName: GetTvShowByName,
                                            val exceptionHandler: SimpleExceptionHandler) {
    fun onStart() {
        getAllPopularTvShows.execute(Unit) {
            result ->
            result.success {
                view.displayTvShow(it)
            }
            result.failure {
                exception ->
                exceptionHandler.notifyException(view, exception)
            }
        }
    }

    fun onTvShowPressed(id: Int) {
        view.navigateToDetailActivity(id)
    }

    fun onSearchTextSubmitted(queryText: String) {
        getTvShowByName.execute(GetTvShowByName.Parameters(queryText)) {
            result ->
            result.success {
                view.displayTvShow(it)
            }
        }
    }
}
