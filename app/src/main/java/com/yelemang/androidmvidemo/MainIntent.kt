package com.yelemang.androidmvidemo

import com.yelemang.androidmvidemo.base.BaseIntent

/**
 * 记录主界面的意图
 */
open class MainIntent:BaseIntent() {
    //登录意图
    class LoginIntent(var account:String,var password:String): MainIntent()
}