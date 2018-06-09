package com.tutrieuchau.kotlin.Service

import android.os.AsyncTask
import com.tutrieuchau.kotlin.Action.CompletedStatus
import com.tutrieuchau.kotlin.Action.RegistCompletedAction
import com.tutrieuchau.kotlin.Data.RegistrationRepo
import com.tutrieuchau.kotlin.Data.User
import com.tutrieuchau.kotlin.Middleware.RegistrationListenerInterface
import com.tutrieuchau.kotlin.mainStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

class RegistrationTask(val registrationListenerInterface: RegistrationListenerInterface,var user: User,val url: String) : AsyncTask<User,Void, RegistrationRepo>() {
    private var retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
    override fun doInBackground(vararg users: User?): RegistrationRepo {
        val networkService = retrofit.create(NetworkService::class.java)
        val registrationRepo = networkService.registration(email = user.email,
                password = user.password,
                fullName = user.fullName,
                sex = user.sex,
                avatarUrl = user.avatarUrl,
                location = user.location).execute().body() as RegistrationRepo
        return RegistrationRepo(success = false,message = "",email = "",fullName = "")
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
    fun registration(email:String?,
                     fullName: String?,
                     password: String?,
                     sex: String?,
                     avatarUrl: String?,
                     location: String?) : Call<RegistrationRepo>
}