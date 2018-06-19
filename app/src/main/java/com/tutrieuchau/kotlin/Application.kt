package com.tutrieuchau.kotlin

import android.app.Application
import com.tutrieuchau.kotlin.Middleware.middleware
import com.tutrieuchau.kotlin.Reducer.appReducer
import com.tutrieuchau.kotlin.Routers.RootRoutable
import com.tutrieuchau.kotlin.States.AppState
import org.rekotlinrouter.Routable
import org.rekotlinrouter.Route
import org.rekotlinrouter.Router
import tw.geothings.rekotlin.Store

var mainStore = Store(
        state = null,
        reducer = ::appReducer,
        middleware = arrayListOf(middleware))
var router:Router<AppState>? = null
class AppController : Application(){
    override fun onCreate() {
        super.onCreate()
        router = Router(
                store = mainStore,
                rootRoutable = RootRoutable(context = applicationContext),
                stateTransform = {subscription -> subscription.select { appState -> appState.navigationState } })
    }
}