package com.tutrieuchau.kotlin.States

import org.rekotlinrouter.HasNavigationState
import org.rekotlinrouter.NavigationState
import tw.geothings.rekotlin.StateType

data class AppState(
        override var navigationState: NavigationState,
        var authenticationState: AuthenticationState,
        var registrationState: RegistrationState
) : StateType, HasNavigationState

data class AuthenticationState(
        var loggedInState: LoggedInState? = null,
        var message: String? = null,
        var token: String? = null,
        var email : String? = null,
        var displayName : String? = null,
        var avatarUrl : String? = null,
        var sex : String? = null,
        var location : String? = null,
        var isFetching: Boolean = false
): StateType

data class RegistrationState(var registerState: RegisterState? = null,
                             var email: String? = null,
                             var fullName: String? = null,
                             var message: String? = null) : StateType

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