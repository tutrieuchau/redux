package com.tutrieuchau.kotlin.Controllers

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.tutrieuchau.kotlin.Action.CompletedStatus
import com.tutrieuchau.kotlin.Action.LoginStartedAction
import com.tutrieuchau.kotlin.Dialog.ProgressDialog
import com.tutrieuchau.kotlin.Dialog.ResultDialog
import com.tutrieuchau.kotlin.R
import com.tutrieuchau.kotlin.Routers.loginIdentifier
import com.tutrieuchau.kotlin.Routers.registrationIdentifier
import com.tutrieuchau.kotlin.Routers.welcomeIdentifier
import com.tutrieuchau.kotlin.States.AppState
import com.tutrieuchau.kotlin.States.AuthenticationState
import com.tutrieuchau.kotlin.States.LoggedInState
import com.tutrieuchau.kotlin.mainStore
import org.rekotlinrouter.RoutingAction
import org.rekotlinrouter.SetRouteAction
import tw.geothings.rekotlin.StoreSubscriber

class Login : Base(), StoreSubscriber<AuthenticationState> {

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
    val progressDialog = ProgressDialog()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            mainStore.dispatch(LoginStartedAction(email = edtEmail.text.toString().trim(),password = edtPassword.text.toString().trim()))
        }

        txtCreate.setOnClickListener {
            val route = arrayListOf(loginIdentifier, registrationIdentifier)
            val action = SetRouteAction(route = route)
            mainStore.dispatch(action)
        }
        // subscribe to state changes
        mainStore.subscribe(this, transform = {it.select { it.authenticationState }.skipRepeats{oldState, newState ->  oldState == newState}})
        initRoute()
    }

    override fun newState(state: AuthenticationState) {
        if (state.loggedInState == LoggedInState.Request){
            progressDialog.show(fragmentManager,"Progress")
        }else if(state.loggedInState == LoggedInState.Error){
            progressDialog.dismiss()
            val route = arrayListOf(loginIdentifier, welcomeIdentifier)
            val action = SetRouteAction(route = route)
            mainStore.dispatch(action)
        }else if(state.loggedInState == LoggedInState.Success){
            progressDialog.dismiss()
            val resultDialog = ResultDialog()
            val bundle = Bundle()
            bundle.putString("title","Failed")
            bundle.putString("message", state.message)
            resultDialog.arguments = bundle
            resultDialog.show(fragmentManager, "Result")
            bundle.putSerializable("type", CompletedStatus.Failed)
        }
    }

    private fun initRoute(){
        val route = arrayListOf(loginIdentifier)
        val action = SetRouteAction(route = route)
        mainStore.dispatch(action)
    }

    override fun onBackPressed() {
    }

}
