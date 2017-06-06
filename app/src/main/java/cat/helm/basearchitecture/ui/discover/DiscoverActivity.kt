package cat.helm.basearchitecture.ui.discover

import android.support.v7.widget.LinearLayoutManager
import cat.helm.basearchitecture.R
import cat.helm.basearchitecture.dependencyinjection.activity.ActivityComponent
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.ui.base.BaseActivity
import cat.helm.basearchitecture.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_discover.*
import javax.inject.Inject

class DiscoverActivity : BaseActivity(), DiscoverView {

    @Inject lateinit var presenter: DiscoverPresenter
    @Inject lateinit var adapter: TvShowAdapter

    override fun onRequestLayout(): Int = R.layout.activity_discover

    override fun injectActivity(component: ActivityComponent) {
        component.inject(this)
    }

    override fun onViewLoaded() {
        presenter.onStart()
        tvShowList.layoutManager = LinearLayoutManager(this)
        tvShowList.adapter = adapter
    }

    override fun displayTvShow(tvShows: List<TvShow>) {
        adapter.tvShows = tvShows
    }

    override fun navigateToDetailActivity(id: Int) {
        startActivity(DetailActivity.getIntent(id, this))
    }
}
