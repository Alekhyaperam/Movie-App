package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.appModule
import com.example.movieapp.di.networkModule
import com.example.movieapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WatchApplication:Application() {
    override  fun onCreate(){
        super.onCreate()
        startKoin{
            androidContext(this@WatchApplication)
            modules(appModule, networkModule,repositoryModule) //register all coin modules
        }
    }
}

//koin is a dependency injection framework
//intializie koin when luanches