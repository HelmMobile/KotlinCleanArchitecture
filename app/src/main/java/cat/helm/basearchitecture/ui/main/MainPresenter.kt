package cat.helm.basearchitecture.ui.main

import cat.helm.basearchitecture.GetSomethingInteractor
import cat.helm.basearchitecture.dependencyinjection.scope.PerActivity
import javax.inject.Inject

/**
 * Created by Borja on 21/12/16.
 */
@PerActivity
class MainPresenter
@Inject constructor(val view: MainView,
                    val getSomethingInteractor: GetSomethingInteractor) {

    fun changeTextButtonPressed() {


        getSomethingInteractor.execute {
            result ->
            result.success {
                value ->
                view.changeText(value)
            }
            result.failure {
                view.changeText("error")
            }
        }
    }

    fun nextActivityButtonPressed() {
        view.navigateToSecondaryActivity()

    }
}