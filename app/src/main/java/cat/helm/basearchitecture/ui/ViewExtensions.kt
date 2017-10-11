package cat.helm.basearchitecture.ui

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Borja on 6/6/17.
 */

fun ImageView.bind(url: String) {
    Picasso.with(this.context).load(url).into(this)
}
