package cat.helm.basearchitecture.ui.discover

import cat.helm.basearchitecture.dependencyinjection.scope.PerActivity
import cat.helm.basearchitecture.interactor.GetAllPopularTvShows
import javax.inject.Inject

/**
 * Created by Borja on 1/6/17.
 */

@PerActivity
class DiscoverPresenter @Inject constructor(val view: DiscoverView,
                                            val getAllPopularTvShows: GetAllPopularTvShows) {
    fun onStart() {
        getAllPopularTvShows.execute(Unit) {
            result ->
            result.success {
                view.displayTvShow(it)
            }
        }
    }

    fun onTvShowPressed(id: Int) {
        view.navigateToDetailActivity(id)
    }
}