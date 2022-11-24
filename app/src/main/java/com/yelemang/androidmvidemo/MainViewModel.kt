package com.yelemang.androidmvidemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel(){
    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.LoadingState)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    /**
     * 处理意图
     */
    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.LoginIntent -> check(it)
                }
            }
        }
    }

    private fun check(loginIntent:MainIntent.LoginIntent) {
        viewModelScope.launch {
            _state.value = MainState.LoadingState
            //模拟接口请求延时
            delay(3000)
            _state.value = try {
                if(loginIntent.account == "1234" && loginIntent.password == "1234"){
                    MainState.LoginSuccessState
                }else{
                    MainState.LoginFailureState(-1,"账号密码错误")
                }
            } catch (e: Exception) {
                MainState.LoginFailureState(0,e.message.toString())
            }
        }
    }
}