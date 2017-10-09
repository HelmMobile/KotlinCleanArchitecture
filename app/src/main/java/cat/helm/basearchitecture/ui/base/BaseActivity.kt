package cat.helm.basearchitecture.ui.base

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import dagger.android.AndroidInjection

/**
 * Created by Borja on 21/12/16.
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(onRequestLayout())
        onViewLoaded()
    }

    abstract fun onRequestLayout(): Int

    abstract fun onViewLoaded()

    override fun showException(exceptionMessage: String) {

        val snack = Snackbar.make(findViewById(android.R.id.content), exceptionMessage, Snackbar.LENGTH_LONG)
        val view = snack.view
        val tv = view.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        tv.setTextColor(Color.WHITE)
        snack.show()
    }


}