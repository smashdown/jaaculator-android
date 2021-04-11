package com.hechikasoft.jaaculator.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface ViewState
interface ViewIntent
interface ViewEffect

abstract class BaseViewModel<intent : ViewIntent, state : ViewState, effect : ViewEffect> : ViewModel() {

    // Create Initial State of View
    private val initialState: ViewState by lazy { createInitialState() }
    abstract fun createInitialState(): ViewState

    val currentState: ViewState
        get() = viewState.value

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(initialState)
    val viewState = _viewState.asStateFlow()

    private val viewIntent: MutableSharedFlow<ViewIntent> = MutableSharedFlow()
    val intent = viewIntent.asSharedFlow()

    private val _viewEffect: Channel<ViewEffect> = Channel()
    val effect = _viewEffect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            intent.collect { handleIntent(it) }
        }
    }

    /**
     * Handle each event
     */
    abstract fun handleIntent(intent: ViewIntent)

    /**
     * Set new Event
     */
    fun setIntent(intent: ViewIntent) {
        viewModelScope.launch { viewIntent.emit(intent) }
    }

    /**
     * Set new Ui State
     */
    protected fun setState(reduce: ViewState.() -> ViewState) {
        val newState = currentState.reduce()
        _viewState.value = newState
    }

    /**
     * Set new Effect
     */
    protected fun setEffect(builder: () -> ViewEffect) {
        val effectValue = builder()
        viewModelScope.launch { _viewEffect.send(effectValue) }
    }
}