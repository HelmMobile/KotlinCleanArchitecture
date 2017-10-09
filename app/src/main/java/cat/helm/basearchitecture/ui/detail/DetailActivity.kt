package cat.helm.basearchitecture.ui.detail

import android.content.Context
import android.content.Intent
import android.widget.TextView
import cat.helm.basearchitecture.R
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.ui.base.BaseActivity
import cat.helm.basearchitecture.ui.bind
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.find
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailView {
    companion object {

        val TV_SHOW_ID: String = "tvShowId"
        fun getIntent(id: Int, context: Context): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(TV_SHOW_ID, id)
            return intent
        }

    }
    @Inject lateinit var presenter: DetailPresenter

    override fun onRequestLayout(): Int = R.layout.activity_detail


    override fun onViewLoaded() {
        val id = intent.extras.getInt(TV_SHOW_ID)
        presenter.onStart(id)
    }

    override fun showTvShow(tvShow: TvShow) {
        with(tvShow) {
            descritpion.text = description
            detailImage.bind(getString(R.string.baseImageUrl)+tvShow.image)
            find<TextView>(R.id.title).text = name
        }
    }
}
