package com.personal.flupertest.application

import android.app.Application
import com.personal.flupertest.di.roomModule
import com.personal.flupertest.di.viewModelModule
import org.koin.android.ext.android.startKoin

class FluperTestApplication :Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
            roomModule,
            viewModelModule
        ))
    }

}