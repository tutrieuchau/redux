package com.tutrieuchau.kotlin.Controllers

import android.os.Bundle
import android.view.View
import android.widget.*
import com.github.ybq.android.spinkit.SpinKitView
import com.tutrieuchau.kotlin.Action.RegistAction
import com.tutrieuchau.kotlin.Action.RegistStartedAction
import com.tutrieuchau.kotlin.R
import com.tutrieuchau.kotlin.States.AppState
import com.tutrieuchau.kotlin.States.RegisterState
import com.tutrieuchau.kotlin.mainStore
import tw.geothings.rekotlin.StoreSubscriber


class Registration : Base(), StoreSubscriber<AppState>{

    private val email : EditText by lazy {
         this.findViewById(R.id.edtEmail) as EditText
    }
    private val displayName : EditText by lazy {
        this.findViewById(R.id.edtDisplayName) as EditText
    }
    private val password: EditText by lazy {
        this.findViewById(R.id.edtPassword) as EditText
    }
    private val location : EditText by lazy {
        this.findViewById(R.id.edtLocation) as EditText
    }
    private val sex : RadioGroup by lazy {
        this.findViewById(R.id.radSex) as RadioGroup
    }
    private val btnRegister: Button by lazy {
        this.findViewById(R.id.btnRegister) as Button
    }
    private val progress: LinearLayout by lazy{
        this.findViewById(R.id.progress) as LinearLayout
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mainStore.subscribe(this)
        btnRegister.setOnClickListener{
            mainStore.dispatch(RegistAction(
                    email = email.text.toString(),
                    fullName = displayName.text.toString(),
                    password = password.text.toString(),
                    location = location.text.toString(),
                    sex = this.findViewById<RadioButton>(sex.checkedRadioButtonId).text.toString(),
                    avatarUrl = ""))
        }
        progress.visibility = View.GONE
    }

    override fun newState(state: AppState) {
        if(state.registrationState.registerState == RegisterState.Request){
            progress.visibility = View.VISIBLE
        }else if (state.registrationState.registerState == RegisterState.Success){
            progress.visibility = View.GONE
            //TODO : Doing something when success
        }else{
            progress.visibility = View.GONE
            //TODO : Show error
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainStore.unsubscribe(this)
    }
}
