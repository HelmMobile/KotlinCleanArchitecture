package cat.helm.basearchitecture.ui.main

import cat.helm.basearchitecture.GetSomethingInteractor
import cat.helm.basearchitecture.Result
import javax.inject.Inject

/**
 * Created by Borja on 21/12/16.
 */
class MainPresenter
    @Inject constructor(val view: MainView,
                        val getSomethingInteractor: GetSomethingInteractor) {

    fun changeTextButtonPressed() {
        getSomethingInteractor.execute{
            result ->
            when(result){
                is Result.Success<String> -> view.changeText(result.result)
                is Result.Error -> view.changeText("error")
            }
        }
    }

    fun nextActivityButtonPressed() {
        view.navigateToSecondaryActivity()

    }
}