package com.tutrieuchau.kotlin.Controllers

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.tutrieuchau.kotlin.R

import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : Base() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    override fun onBackPressed() {
    }

}
