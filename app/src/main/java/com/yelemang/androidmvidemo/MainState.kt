package com.yelemang.androidmvidemo

open class MainState {
    object LoadingState : MainState()
    object LoginSuccessState : MainState()
    class LoginFailureState(var errorCode: Int, var errorMsg: String) : MainState()
}