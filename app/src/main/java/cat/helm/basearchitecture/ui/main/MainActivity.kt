package cat.helm.basearchitecture.ui.main

import cat.helm.basearchitecture.R
import cat.helm.basearchitecture.Result
import cat.helm.basearchitecture.dependencyinjection.activity.ActivityComponent
import cat.helm.basearchitecture.ui.base.BaseActivity
import cat.helm.basearchitecture.ui.second.SecondaryActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var presenter : MainPresenter

    override fun injectActivity(component: ActivityComponent) {
        component.inject(this)
    }

    override fun onRequestLayout(): Int {
        var mao = Result.Failure()
        return R.layout.activity_main
    }

    override fun onViewLoaded() {
        changeTextButton.setOnClickListener {
            presenter.changeTextButtonPressed()
        }
        next_activity_button.setOnClickListener {
            presenter.nextActivityButtonPressed()
        }
    }

    override fun changeText(s: String) {
        text.text = s
    }

    override fun navigateToSecondaryActivity() {
        startActivity<SecondaryActivity>()
    }
}
