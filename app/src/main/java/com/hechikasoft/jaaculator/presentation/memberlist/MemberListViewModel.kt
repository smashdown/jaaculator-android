package com.hechikasoft.jaaculator.presentation.memberlist

import com.hechikasoft.jaaculator.presentation.base.BaseViewModel
import com.hechikasoft.jaaculator.presentation.base.ViewIntent
import com.hechikasoft.jaaculator.presentation.base.ViewState
import com.hechikasoft.jaaculator.presentation.memberlist.composable.exampleUiState

class MemberListViewModel : BaseViewModel<MemberListContract.Intent, MemberListContract.State, MemberListContract.Effect>() {
    override fun createInitialState(): ViewState {
//        return MemberListContract.State(
//            MemberListContract.RandomNumberState.Idle
//        )
        return exampleUiState
    }

    override fun handleIntent(intent: ViewIntent) {
        when (intent) {
            is MemberListContract.Intent.OnRandomNumberClicked -> {
                generateRandomNumber()
            }
            is MemberListContract.Intent.OnShowToastClicked -> {
                setEffect { MemberListContract.Effect.ShowToast }
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