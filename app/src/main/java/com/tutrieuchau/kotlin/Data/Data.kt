package com.tutrieuchau.kotlin.Data

import com.google.gson.annotations.SerializedName
import java.util.*

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