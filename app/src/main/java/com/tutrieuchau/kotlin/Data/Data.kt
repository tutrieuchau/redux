package com.tutrieuchau.kotlin.Data

import com.google.gson.annotations.SerializedName

val BASE_URL  = "http://192.168.11.165:1234"
data class User(var email : String?,
                var fullName : String?,
                var password: String?,
                var avatarUrl: String?,
                var sex: String?,
                var location: String?)
data class RegistrationRepo(
        @SerializedName("success") var success : Boolean,
        @SerializedName("message") var message: String,
        @SerializedName("email") var email: String,
        @SerializedName("fullname") var fullName: String)
data class LoginRepo(
        @SerializedName("success") var success : Boolean,
        @SerializedName("message") var message: String,
        @SerializedName("token") var token: String
)