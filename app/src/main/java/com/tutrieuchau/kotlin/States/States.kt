package com.tutrieuchau.kotlin.States

import com.tutrieuchau.kotlin.State.LoggedInState
import org.rekotlinrouter.HasNavigationState
import org.rekotlinrouter.NavigationState
import tw.geothings.rekotlin.StateType

data class AppState(
        override var navigationState: NavigationState,
        var authenticationState: AuthenticationState
) : StateType, HasNavigationState

data class AuthenticationState(
        var loggedInState: LoggedInState? = null,
        var errorMessage: String? = null,
        var token: String? = null,
        var email : String? = null,
        var displayName : String? = null,
        var avatarUrl : String? = null,
        var sex : String? = null,
        var location : String? = null
): StateType

data class RegistrationState(var registerState: RegisterState) : StateType

enum class LoggedInState{
    Request,
    Success,
    Error
}
enum class RegisterState{
    Request,
    Success,
    Error
}