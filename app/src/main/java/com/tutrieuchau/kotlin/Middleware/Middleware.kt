package com.tutrieuchau.kotlin.Middleware

import com.tutrieuchau.kotlin.Action.LoginCompletedAction
import com.tutrieuchau.kotlin.Action.LoginStartedAction
import com.tutrieuchau.kotlin.Action.RegistCompletedAction
import com.tutrieuchau.kotlin.Action.RegistStartedAction
import com.tutrieuchau.kotlin.Controllers.Login
import com.tutrieuchau.kotlin.Data.BASE_URL
import com.tutrieuchau.kotlin.Data.User
import com.tutrieuchau.kotlin.Service.LoginTask
import com.tutrieuchau.kotlin.Service.RegistrationTask
import com.tutrieuchau.kotlin.States.AppState
import tw.geothings.rekotlin.DispatchFunction
import tw.geothings.rekotlin.Middleware
import tw.geothings.rekotlin.Store

interface LoginListenerInterface{
    fun onFinish(result: LoginCompletedAction, store:Store<AppState>)
}
class LoginListenerMiddleware : LoginListenerInterface{
    override fun onFinish(result: LoginCompletedAction, store: Store<AppState>) {
        store.dispatch(result)
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
                is RegistStartedAction ->{
                    executeRegistration(action, dispatch)
                }
                is LoginStartedAction ->{
                    executeLogin(action, dispatch)
                }
            }
            next(action)
        }
    }
}

fun executeRegistration(action: RegistStartedAction, dispatch: DispatchFunction){
    val registrationMiddleware = RegistrationListenerMiddleware()
    val user = User(
            email = action.email,
            fullName = action.fullName,
            password = action.password,
            avatarUrl = action.avatarUrl,
            sex = action.sex,
            location = action.location)
    val registrationTask = RegistrationTask(registrationMiddleware, user = user, url = BASE_URL)
    registrationTask.execute()
}
fun executeLogin(action: LoginStartedAction, dispatch: DispatchFunction){
    val loginMiddleware = LoginListenerMiddleware()
    val user = User(
            email = action.email,
            password = action.password,
            fullName = "",
            avatarUrl = "",
            sex = "",
            location = ""
    )
    val loginTask = LoginTask(loginListenerInterface = loginMiddleware, user = user, url = BASE_URL)
    loginTask.execute()
}