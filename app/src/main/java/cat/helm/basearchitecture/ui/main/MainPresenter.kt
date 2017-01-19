package cat.helm.basearchitecture.ui.main

import cat.helm.basearchitecture.GetSomethingInteractor
import cat.helm.basearchitecture.Result
import javax.inject.Inject

/**
 * Created by Borja on 21/12/16.
 */
class MainPresenter
    @Inject constructor(val view: MainView,
                        val mao: GetSomethingInteractor) {

    fun changeTextButtonPressed() {
        mao.execute{
            result ->
            when(result){
                is Result.Success<*> -> view.changeText(result.result as String)
                is Result.Error -> view.changeText("error")
            }
        }
    }

    fun nextActivityButtonPressed() {
        view.navigateToSecondaryActivity()

    }
}