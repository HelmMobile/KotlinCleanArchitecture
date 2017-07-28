package cat.helm.basearchitecture.ui.main

import cat.helm.basearchitecture.R
import cat.helm.basearchitecture.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var presenter: MainPresenter

    override fun onRequestLayout(): Int = R.layout.activity_main


    override fun onViewLoaded() {
        presenter.pastel()
    }


}
