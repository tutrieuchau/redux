package com.tutrieuchau.kotlin.Action

import tw.geothings.rekotlin.Action

class LoginAction {
//    constructor()
    data class Request(var username: String,var password: String): Action
    data class Success(var token: String): Action
    data class Error(var message: String): Action
}
class RegiterAction{
    data class Request(var username: String):Action
    data class Success(var username: String): Action
    data class Error(var message: String): Action
}
