package com.tutrieuchau.kotlin.Middleware

import com.tutrieuchau.kotlin.Action.LoginAction
import com.tutrieuchau.kotlin.Action.RegistAction
import com.tutrieuchau.kotlin.Action.RegistCompletedAction
import com.tutrieuchau.kotlin.Action.RegistStartedAction
import com.tutrieuchau.kotlin.Data.User
import com.tutrieuchau.kotlin.Service.RegistrationTask
import com.tutrieuchau.kotlin.States.AppState
import tw.geothings.rekotlin.DispatchFunction
import tw.geothings.rekotlin.Middleware
import tw.geothings.rekotlin.Store

interface LoginListenerInterface{
    fun onFinish(result: LoginAction, store:Store<AppState>)
}
class LoginListenerMiddleware : LoginListenerInterface{
    override fun onFinish(result: LoginAction, store: Store<AppState>) {

    }
}
interface RegistrationListenerInterface{
    fun onFinish(result: RegistCompletedAction, store: Store<AppState>)
}
class RegistrationListenerMiddleware: RegistrationListenerInterface{
    override fun onFinish(result: RegistCompletedAction, store: Store<AppState>) {
        store.dispatch(result)
    }
}

internal val middleware : Middleware<AppState> = { dispatch, getstate ->
    { next ->
        {action ->
            when(action){
                is RegistAction ->{
                    executeRegistration(action, dispatch)
                }
            }
        }
    }
}

fun executeRegistration(action: RegistAction, dispatch: DispatchFunction){
    val registrationMiddleware = RegistrationListenerMiddleware()
    val user = User(
            email = action.email,
            fullName = action.fullName,
            password = action.password,
            avatarUrl = action.avatarUrl,
            sex = action.sex,
            location = action.location)
    val registrationTask = RegistrationTask(registrationMiddleware, user = user, url = "http://127.0.0.1:1234")
    registrationTask.execute()
    dispatch(RegistStartedAction(email = action.email))
}