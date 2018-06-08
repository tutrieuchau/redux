package com.tutrieuchau.kotlin.State

import org.rekotlinrouter.HasNavigationState
import org.rekotlinrouter.NavigationState
import tw.geothings.rekotlin.StateType

data class AppState(
        override var navigationState: NavigationState,
        var authenticationState: AuthenticationState = AuthenticationState()
):StateType, HasNavigationState