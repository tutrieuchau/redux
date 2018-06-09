package com.tutrieuchau.kotlin.Action

import tw.geothings.rekotlin.Action

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

data class RegistAction( var email : String,
                          var fullName : String,
                          var password: String,
                          var avatarUrl: String,
                          var sex: String,
                          var location: String): Action
data class RegistStartedAction(var email: String) : Action
data class RegistCompletedAction(var completedStatus: CompletedStatus? = CompletedStatus.Success, var message:String?, var email: String?, var fullName: String?): Action
enum class CompletedStatus{
    Success,
    Failed
}
