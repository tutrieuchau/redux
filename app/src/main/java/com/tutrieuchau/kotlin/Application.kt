package com.tutrieuchau.kotlin

import android.app.Application
import com.tutrieuchau.kotlin.Middleware.middleware
import com.tutrieuchau.kotlin.Reducer.appReducer
import com.tutrieuchau.kotlin.States.AppState
import org.rekotlinrouter.Routable
import org.rekotlinrouter.Route
import org.rekotlinrouter.Router
import tw.geothings.rekotlin.Store

var mainStore = Store(
        state = null,
        reducer = ::appReducer,
        middleware = arrayListOf(middleware))
//var router = Router(
//        store = mainStore,
//        rootRoutable = RootR)
class AppController : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}