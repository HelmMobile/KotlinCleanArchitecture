package cat.helm.basearchitecture.ui.main

import cat.helm.basearchitecture.ui.base.BaseView

/**
 * Created by Borja on 21/12/16.
 */
interface MainView : BaseView {

    fun changeText(s: String)
    fun navigateToSecondaryActivity()


}