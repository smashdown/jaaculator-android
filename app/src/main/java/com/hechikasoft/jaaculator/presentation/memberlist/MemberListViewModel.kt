package com.hechikasoft.jaaculator.presentation.memberlist

import androidx.lifecycle.viewModelScope
import com.hechikasoft.jaaculator.presentation.base.BaseViewModel
import com.hechikasoft.jaaculator.presentation.base.ViewIntent
import com.hechikasoft.jaaculator.presentation.base.ViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MemberListViewModel:BaseViewModel<MemberContract.Intent, MemberContract.State, MemberContract.Effect>() {
    override fun createInitialState(): ViewState {
        return MemberContract.State(
            MemberContract.RandomNumberState.Idle
        )
    }

    override fun handleIntent(intent: ViewIntent) {
        when (intent) {
            is MemberContract.Intent.OnRandomNumberClicked -> { generateRandomNumber() }
            is MemberContract.Intent.OnShowToastClicked -> {
                setEffect { MemberContract.Effect.ShowToast }
            }
        }
    }


    /**
     * Generate a random number
     */
    private fun generateRandomNumber() {
//        viewModelScope.launch {
//            // Set Loading
//            setState {  copy(randomNumberState = MemberContract.RandomNumberState.Loading) }
//            try {
//                delay(5000)
//                val random = (0..10).random()
//                if (random % 2 == 0) {
//                    setState { copy(randomNumberState = MemberContract.RandomNumberState.Idle) }
//                    throw RuntimeException("Number is even")
//                }
//                setState { copy(randomNumberState = MemberContract.RandomNumberState.Success(number = random)) }
//            } catch (exception : Exception) {
//                setEffect { MemberContract.Effect.ShowToast }
//            }
//        }
    }
}