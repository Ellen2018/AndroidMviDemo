package com.yelemang.androidmvidemo.ext

import android.app.Activity
import android.view.View
import android.widget.Toast

fun View.click(block: (View) -> Unit) = setOnClickListener {
    block.invoke(it)
}

fun Activity.toast(content:String){
    Toast.makeText(this,content,Toast.LENGTH_SHORT).show()
}