package com.yelemang.androidmvidemo

import com.yelemang.androidmvidemo.base.BaseState

open class MainState : BaseState() {
    object LoginSuccessState : MainState()
    class LoginFailureState(var errorCode: Int, var errorMsg: String) : MainState()
}