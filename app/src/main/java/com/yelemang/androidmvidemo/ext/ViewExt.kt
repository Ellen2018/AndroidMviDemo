package com.yelemang.androidmvidemo.ext

import android.view.View

fun View.click(block: (View) -> Unit) = setOnClickListener {
    block.invoke(it)
}