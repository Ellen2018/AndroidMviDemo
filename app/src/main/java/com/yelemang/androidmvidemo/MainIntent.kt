package com.yelemang.androidmvidemo

/**
 * 记录主界面的意图
 */
open class MainIntent {
    //登录意图
    class LoginIntent(var account:String,var password:String): MainIntent()
}