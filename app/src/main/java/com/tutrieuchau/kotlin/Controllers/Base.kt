package com.tutrieuchau.kotlin.Controllers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

open class Base: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var rootView:View = window.decorView
        var uiOption = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        rootView.systemUiVisibility = uiOption
    }
}