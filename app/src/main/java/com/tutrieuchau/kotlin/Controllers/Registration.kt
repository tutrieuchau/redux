package com.tutrieuchau.kotlin.Controllers

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.tutrieuchau.kotlin.Action.CompletedStatus
import com.tutrieuchau.kotlin.Action.RegistStartedAction
import com.tutrieuchau.kotlin.Dialog.ProgressDialog
import com.tutrieuchau.kotlin.Dialog.ResultDialog
import com.tutrieuchau.kotlin.Dialog.ResultDialogCallBackInteface
import com.tutrieuchau.kotlin.R
import com.tutrieuchau.kotlin.States.RegisterState
import com.tutrieuchau.kotlin.States.RegistrationState
import com.tutrieuchau.kotlin.mainStore
import tw.geothings.rekotlin.StoreSubscriber


class Registration : Base(), StoreSubscriber<RegistrationState>, ResultDialogCallBackInteface{
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
    val progressDialog = ProgressDialog()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mainStore.subscribe(this , transform = {it.select { it.registrationState }.skipRepeats{oldState, newState ->oldState == newState}})
        btnRegister.setOnClickListener{
            mainStore.dispatch(RegistStartedAction(
                    email = email.text.toString(),
                    fullName = displayName.text.toString(),
                    password = password.text.toString(),
                    location = location.text.toString(),
                    sex = this.findViewById<RadioButton>(sex.checkedRadioButtonId).text.toString(),
                    avatarUrl = ""))

        }
    }

    override fun newState(state: RegistrationState) {
        if(state.registerState == RegisterState.Request){
            progressDialog.show(fragmentManager,"Progress")
        }else if (state.registerState == RegisterState.Success){
            progressDialog.dismiss()
            val resultDialog = ResultDialog()
            val bundle = Bundle()
            bundle.putString("title","Success")
            bundle.putString("message", "WellCome To Kotlin")
            bundle.putSerializable("type", CompletedStatus.Success)
            resultDialog.arguments = bundle
            resultDialog.show(fragmentManager, "Result")
        }else if(state.registerState == RegisterState.Error){
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

    override fun onDestroy() {
        super.onDestroy()
        mainStore.unsubscribe(this)
    }

    override fun onDismiss(status: CompletedStatus) {
        if(status == CompletedStatus.Success){
            //TODO: Router to login
        }

    }
}
