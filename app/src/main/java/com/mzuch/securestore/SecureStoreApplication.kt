package com.mzuch.securestore

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SecureStoreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SecureStoreApplication)
            modules(appModule)
        }
    }
}