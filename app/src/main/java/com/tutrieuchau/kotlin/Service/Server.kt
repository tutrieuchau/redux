package com.tutrieuchau.kotlin.Service

import android.os.AsyncTask
import com.tutrieuchau.kotlin.Action.CompletedStatus
import com.tutrieuchau.kotlin.Action.LoginCompletedAction
import com.tutrieuchau.kotlin.Action.RegistCompletedAction
import com.tutrieuchau.kotlin.Data.LoginRepo
import com.tutrieuchau.kotlin.Data.RegistrationRepo
import com.tutrieuchau.kotlin.Data.User
import com.tutrieuchau.kotlin.Middleware.LoginListenerInterface
import com.tutrieuchau.kotlin.Middleware.RegistrationListenerInterface
import com.tutrieuchau.kotlin.mainStore
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

class RegistrationTask(val registrationListenerInterface: RegistrationListenerInterface,var user: User,val url: String) : AsyncTask<User,Void, RegistrationRepo>() {
    private var retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
    override fun doInBackground(vararg users: User?): RegistrationRepo {
        val networkService = retrofit.create(NetworkService::class.java)
        var registrationRepo = RegistrationRepo(success = false,message = "",email = "",fullName = "")
        try {
            registrationRepo = networkService.registration(user).execute().body() as RegistrationRepo
        }catch (e:Exception){
            e.printStackTrace()
            return RegistrationRepo(success = false, message = "SocketTimeout",fullName = "", email = "")
        }
        return registrationRepo
    }

    override fun onPostExecute(registrationRepo : RegistrationRepo) {
        super.onPostExecute(registrationRepo)
        var registCompletedAction = RegistCompletedAction(CompletedStatus.Failed,"Internal Error",null,null)
        if (registrationRepo.success){
            registCompletedAction.completedStatus = CompletedStatus.Success
            registCompletedAction.email = registrationRepo.email
            registCompletedAction.fullName = registrationRepo.fullName
            registrationListenerInterface.onFinish(registCompletedAction, mainStore)
        }else{
            registCompletedAction.completedStatus = CompletedStatus.Failed
            registCompletedAction.message = registrationRepo.message
            registrationListenerInterface.onFinish(registCompletedAction, mainStore)
        }
    }
}
interface NetworkService{
    @POST("/registration")
    fun registration(@Body user: User) : Call<RegistrationRepo>
    @POST("/login")
    fun login(@Body user: User) : Call<LoginRepo>
}
class LoginTask(val loginListenerInterface: LoginListenerInterface,val user: User,val url: String): AsyncTask<Void,Void,LoginRepo>(){
    private var retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
    override fun doInBackground(vararg params: Void?): LoginRepo {
        val networkService = retrofit.create(NetworkService::class.java)
        var loginRepo = LoginRepo(success = false,message = "",token = "")
        try {
            loginRepo = networkService.registration(user).execute().body() as LoginRepo
        }catch (e:Exception){
            e.printStackTrace()
            return LoginRepo(success = false, message = "SocketTimeout",token = "")
        }
        return loginRepo
    }
    override fun onPostExecute(loginRepo: LoginRepo) {
        var loginCompletedAction = LoginCompletedAction(completedStatus = CompletedStatus.Failed,token = "",message = "")
        if(loginRepo.success){
            loginCompletedAction.completedStatus = CompletedStatus.Success
            loginCompletedAction.token = loginRepo.token
        }else{
            loginCompletedAction.completedStatus = CompletedStatus.Failed
            loginCompletedAction.message = loginRepo.message
        }
        loginListenerInterface.onFinish(loginCompletedAction, mainStore)
    }
}