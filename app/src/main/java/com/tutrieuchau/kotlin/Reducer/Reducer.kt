package com.tutrieuchau.kotlin.Reducer


import com.tutrieuchau.kotlin.Action.*
import com.tutrieuchau.kotlin.States.*
import org.rekotlinrouter.NavigationReducer
import org.rekotlinrouter.NavigationState
import org.rekotlinrouter.SetRouteAction
import org.rekotlinrouter.SetRouteSpecificData
import tw.geothings.rekotlin.Action

fun appReducer(action: Action, oldState:AppState?) : AppState {
    val state = oldState ?: AppState(
            navigationState = NavigationReducer.handleAction(action = action, state = oldState?.navigationState),
            authenticationState = authenticationReducer(action = action, state = oldState?.authenticationState),
            registrationState = registrationReducer(action = action, state = oldState?.registrationState))
    return state.copy(
            navigationState = (::navigationReducer)(action, state.navigationState),
            authenticationState = authenticationReducer(action = action, state = state.authenticationState),
            registrationState = registrationReducer(action = action, state = state.registrationState)
    )
}
fun authenticationReducer( action: Action, state: AuthenticationState?) : AuthenticationState{
    var newState = state?: AuthenticationState()
    when (action){
        is LoginAction.LoginStarted -> {
            return newState.copy(loggedInState = LoggedInState.Request)
        }
    }
    return newState
}
fun registrationReducer(action: Action, state: RegistrationState?) : RegistrationState{
    val newState = state?: RegistrationState()
    when (action){
        is RegistStartedAction -> {
            return newState.copy(registerState = RegisterState.Request)
        }
        is RegistCompletedAction -> {
            if(action.completedStatus == CompletedStatus.Success){
                return newState.copy(
                        registerState = RegisterState.Success,
                        email = action.email,
                        fullName = action.fullName
                )
            }else{
                return newState.copy(registerState = RegisterState.Error,message = action.message)
            }
        }

    }
    return newState
}
fun navigationReducer(action: Action, oldState: NavigationState) : NavigationState{
    val state =  oldState ?: NavigationReducer.handleAction(action = action, state = oldState)
    when (action) {
        is SetRouteAction -> {
            return NavigationReducer.handleAction(action = action, state = state)
        }

        is SetRouteSpecificData -> {
            return NavigationReducer.handleAction(action = action, state = state)
        }
    }
    return state
}
