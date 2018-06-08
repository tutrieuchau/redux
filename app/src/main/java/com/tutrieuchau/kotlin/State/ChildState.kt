package com.tutrieuchau.kotlin.State

enum class MainState{
    Authentication,
    Register
}
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