package com.yelemang.androidmvidemo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : BaseIntent> : ViewModel() {
    val intent = Channel<I>(Channel.UNLIMITED)
    val state: MutableStateFlow<BaseState> = MutableStateFlow(BaseState.InitState)

    init {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                handleIntent(it)
            }
        }
    }

    /**
     * 处理意图
     */
    protected abstract fun handleIntent(intent: I)
}