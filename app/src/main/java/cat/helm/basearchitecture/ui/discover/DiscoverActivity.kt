package cat.helm.basearchitecture.ui.discover

import android.content.Context
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import cat.helm.basearchitecture.R
import cat.helm.basearchitecture.R.id.tvShowList
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.ui.base.BaseActivity
import cat.helm.basearchitecture.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_discover.*
import javax.inject.Inject

class DiscoverActivity : BaseActivity(), DiscoverView {

    @Inject lateinit var presenter: DiscoverPresenter
    @Inject lateinit var adapter: TvShowAdapter

    override fun onRequestLayout(): Int = R.layout.activity_discover

    override fun onViewLoaded() {
        presenter.onStart()
        tvShowList.layoutManager = LinearLayoutManager(this)
        tvShowList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        setUpSearchView(menu)

        return true

    }

    private fun setUpSearchView(menu: Menu) {
        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.search)) as? SearchView
        searchView?.apply {
            setIconifiedByDefault(false)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    presenter.onSearchTextSubmitted(query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    presenter.onSearchTextSubmitted(newText)
                    return true
                }

            })
            MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search),
                    object : MenuItemCompat.OnActionExpandListener {
                        override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                            searchView.requestFocus()
                            showSoftKeyboard()
                            return true
                        }

                        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                            hideSoftKeyboard()
                            return true
                        }
                    })
        }
    }

    override fun displayTvShow(tvShows: List<TvShow>) {
        adapter.tvShows = tvShows
    }

    override fun navigateToDetailActivity(id: Int) {
        startActivity(DetailActivity.getIntent(id, this))
    }

    private fun hideSoftKeyboard() {
        val view = currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE)
                    as? InputMethodManager)?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showSoftKeyboard() {
        val view = currentFocus
        if (view != null) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.showSoftInput(view, 0)
        }
    }
}
