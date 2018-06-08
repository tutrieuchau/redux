package com.tutrieuchau.kotlin.Reducer

import com.tutrieuchau.kotlin.Action.LoginAction
import com.tutrieuchau.kotlin.State.AppState
import com.tutrieuchau.kotlin.State.LoggedInState
import org.rekotlinrouter.NavigationReducer
import tw.geothings.rekotlin.Action

fun appReducer(action: Action, oldState:AppState?) : AppState{
    var state = oldState ?: AppState(navigationState = NavigationReducer.handleAction(action= action,state = oldState?.navigationState))
    when(action){
        is LoginAction.Request -> {
            state = state.copy(authenticationState = state.authenticationState.copy(loggedInState = LoggedInState.Request))
        }
        is LoginAction.Success -> {
            state = state.copy(authenticationState = state.authenticationState.copy(loggedInState = LoggedInState.Success, token = action.token))
        }
    }
    return state
}