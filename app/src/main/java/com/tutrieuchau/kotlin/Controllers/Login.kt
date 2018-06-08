package com.tutrieuchau.kotlin.Controllers

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.tutrieuchau.kotlin.Action.LoginAction
import com.tutrieuchau.kotlin.R
import com.tutrieuchau.kotlin.State.AppState
import com.tutrieuchau.kotlin.State.LoggedInState
import com.tutrieuchau.kotlin.mainStore
import tw.geothings.rekotlin.StoreSubscriber

class Login : Base(), StoreSubscriber<AppState> {

    private val edtEmail : TextView by lazy {
        this.findViewById(R.id.edtEmail) as EditText
    }
    private val edtPassword : TextView by lazy {
        this.findViewById(R.id.edtPassword) as EditText
    }
    private val txtCreate : TextView by lazy {
        this.findViewById(R.id.txtCreate) as TextView
    }
    private val btnLogin : TextView by lazy {
        this.findViewById(R.id.btnLogin) as Button
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            mainStore.dispatch(LoginAction.Request(username = edtEmail.text.toString(), password = edtPassword.text.toString()))
        }

        txtCreate.setOnClickListener {
        }

        // subscribe to state changes
        mainStore.subscribe(this)

    }


    override fun newState(state: AppState) {
        val authenticationState = state.authenticationState
        when (authenticationState.loggedInState) {
            LoggedInState.Request -> {
                //
            }
        }
    }
}
