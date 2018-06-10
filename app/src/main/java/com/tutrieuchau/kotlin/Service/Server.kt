package com.tutrieuchau.kotlin.Service

import android.os.AsyncTask
import com.tutrieuchau.kotlin.Action.CompletedStatus
import com.tutrieuchau.kotlin.Action.RegistCompletedAction
import com.tutrieuchau.kotlin.Data.RegistrationRepo
import com.tutrieuchau.kotlin.Data.User
import com.tutrieuchau.kotlin.Middleware.RegistrationListenerInterface
import com.tutrieuchau.kotlin.mainStore
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.net.ConnectException

class RegistrationTask(val registrationListenerInterface: RegistrationListenerInterface,var user: User,val url: String) : AsyncTask<User,Void, RegistrationRepo>() {
    private var retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
    override fun doInBackground(vararg users: User?): RegistrationRepo {
        val networkService = retrofit.create(NetworkService::class.java)
        var registrationRepo = RegistrationRepo(success = false,message = "",email = "",fullName = "")
        try {
            registrationRepo = networkService.registration(user).execute().body() as RegistrationRepo
        }catch (e:ConnectException){
            e.printStackTrace()
        }
        return registrationRepo
    }

    override fun onPostExecute(registrationRepo : RegistrationRepo) {
        super.onPostExecute(registrationRepo)
        var registCompletedAction = RegistCompletedAction(CompletedStatus.Success,null,null,null)
        if (registrationRepo.success){
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
}