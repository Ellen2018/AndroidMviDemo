package com.yelemang.androidmvidemo

import androidx.lifecycle.viewModelScope
import com.yelemang.androidmvidemo.base.BaseState
import com.yelemang.androidmvidemo.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : BaseViewModel<MainIntent>(){

    private fun check(loginIntent:MainIntent.LoginIntent) {
        viewModelScope.launch {
            state.value = BaseState.LoadingState
            //模拟接口请求延时
            delay(3000)
            state.value = try {
                if(loginIntent.account == "1234" && loginIntent.password == "1234"){
                    MainState.LoginSuccessState
                }else{
                    MainState.LoginFailureState(-1,"账号密码错误")
                }
            } catch (e: Exception) {
                MainState.LoginFailureState(0,e.message.toString())
            }
            state.value = BaseState.HideState
        }
    }

    override fun handleIntent(intent: MainIntent) {
        when(intent){
            is MainIntent.LoginIntent->{
                check(intent)
            }
        }
    }
}