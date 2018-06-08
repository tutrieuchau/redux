package com.tutrieuchau.kotlin.State

import tw.geothings.rekotlin.StateType

data class AuthenticationState(
        var loggedInState: LoggedInState? = null,
        var token: String? = null,
        var errorMessage: String? = null
): StateType