package cat.helm.basearchitecture.ui.discover

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.helm.basearchitecture.R
import cat.helm.basearchitecture.model.TvShow
import cat.helm.basearchitecture.ui.bind
import kotlinx.android.synthetic.main.item_tv_show.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by Borja on 2/6/17.
 */
class TvShowAdapter @Inject constructor(val discoverPresenter: DiscoverPresenter) : RecyclerView.Adapter<TvShowViewHolder>() {

    var tvShows: List<TvShow> by Delegates.observable(emptyList()) {
        _, old, new->
        autoNotify(old, new) {
            old, new -> old.id == new.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {

        holder.bind(tvShows[position], discoverPresenter)
    }

    override fun getItemCount(): Int = tvShows.size

    fun autoNotify(oldList: List<TvShow>, newList: List<TvShow>, compare: (TvShow, TvShow) -> Boolean) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

                return compare(oldList[oldItemPosition], newList[newItemPosition])

            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

                return oldList[oldItemPosition] == newList[newItemPosition]

            }

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size

        })

        diff.dispatchUpdatesTo(this)
    }

}

class TvShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(tvShow: TvShow, discoverPresenter: DiscoverPresenter) {
        itemView.tv_show_name.text = tvShow.name
        itemView.image.bind(itemView.context.getString(R.string.baseImageUrl) + tvShow.image)
        itemView.setOnClickListener {
            discoverPresenter.onTvShowPressed(tvShow.id)
        }
    }

}
