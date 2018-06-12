package com.tutrieuchau.kotlin.Action

import com.tutrieuchau.kotlin.Data.User
import com.tutrieuchau.kotlin.Middleware.RegistrationListenerMiddleware
import com.tutrieuchau.kotlin.Service.RegistrationTask
import com.tutrieuchau.kotlin.States.AppState
import com.tutrieuchau.kotlin.States.RegistrationState
import tw.geothings.rekotlin.Action
import tw.geothings.rekotlin.AsyncActionCreator
import tw.geothings.rekotlin.DispatchCallback
import tw.geothings.rekotlin.Store
import java.util.*


class LoginAction {
    data class LoginStarted(var username: String,var password: String): Action
    data class Login( var username: String): Action
    data class LoginCompleted(var email: String,
                              var fullName: String,
                              var token: String,
                              var message: String,
                              var sex: String,
                              var avatarUrl: String,
                              var location: String): Action
}

data class RegistStartedAction( var email : String,
                          var fullName : String,
                          var password: String,
                          var avatarUrl: String,
                          var sex: String,
                          var location: String): Action
data class RegistCompletedAction(var completedStatus: CompletedStatus? = CompletedStatus.Success, var message:String?, var email: String?, var fullName: String?): Action
enum class CompletedStatus{
    Success,
    Failed
}
