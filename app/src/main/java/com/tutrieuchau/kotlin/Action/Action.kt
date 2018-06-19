package com.tutrieuchau.kotlin.Action

import tw.geothings.rekotlin.Action


data class LoginStartedAction(var email: String,var password: String): Action
data class LoginCompletedAction(
        var completedStatus: CompletedStatus?,
        var token:String?, var message: String?): Action
data class RegistStartedAction( var email : String,
                          var fullName : String,
                          var password: String,
                          var avatarUrl: String,
                          var sex: String,
                          var location: String): Action
data class RegistCompletedAction(var completedStatus: CompletedStatus? = CompletedStatus.Failed, var message:String?, var email: String?, var fullName: String?): Action
enum class CompletedStatus{
    Success,
    Failed
}
