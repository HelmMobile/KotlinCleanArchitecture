package cat.helm.basearchitecture.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cat.helm.basearchitecture.Application
import cat.helm.basearchitecture.dependencyinjection.activity.ActivityComponent
import cat.helm.basearchitecture.dependencyinjection.activity.ActivityModule
import cat.helm.basearchitecture.dependencyinjection.activity.ViewModule

/**
 * Created by Borja on 21/12/16.
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(onRequestLayout())
        initializeInjection()
        onViewLoaded()
    }

    abstract fun onRequestLayout(): Int

    fun initializeInjection() {
        val component = (application as Application).component.plus(ActivityModule(this), ViewModule(this))
        injectActivity(component)
    }

    abstract fun injectActivity(component: ActivityComponent)

    abstract fun onViewLoaded()
}
