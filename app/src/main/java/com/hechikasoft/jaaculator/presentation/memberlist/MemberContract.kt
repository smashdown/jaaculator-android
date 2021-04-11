package com.hechikasoft.jaaculator.presentation.memberlist

import com.hechikasoft.jaaculator.presentation.base.ViewEffect
import com.hechikasoft.jaaculator.presentation.base.ViewIntent
import com.hechikasoft.jaaculator.presentation.base.ViewState

class MemberContract {

    // Events that user performed
    sealed class Intent : ViewIntent {
        object OnRandomNumberClicked : Intent()
        object OnShowToastClicked : Intent()
    }

    // Ui View States
    data class State(
        val randomNumberState: RandomNumberState
    ) : ViewState

    // View State that related to Random Number
    sealed class RandomNumberState {
        object Idle : RandomNumberState()
        object Loading : RandomNumberState()
        data class Success(val number: Int) : RandomNumberState()
    }

    // Side effects
    sealed class Effect : ViewEffect {
        object ShowToast : Effect()
    }

}