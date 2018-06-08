package com.tutrieuchau.kotlin

import android.app.Application
import com.tutrieuchau.kotlin.Reducer.appReducer
import tw.geothings.rekotlin.Store

var mainStore = Store(state = null,
        reducer = ::appReducer,
        middleware = emptyList())
//var router = Router(store = mainStore)
class AppController : Application()