package com.mzuch.securestore

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.mzuch.securestore.getscreen.viewmodel.GetScreenViewModel
import com.mzuch.securestore.repository.ValueRepository
import com.mzuch.securestore.repository.ValueRepositoryImpl
import com.mzuch.securestore.savescreen.viewmodel.SaveScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single(createdAtStart = true) {
        val masterKey = MasterKey.Builder(androidContext())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        EncryptedSharedPreferences.create(
            androidContext(),
            "secure_store_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    factory<ValueRepository> { ValueRepositoryImpl(get()) }
    viewModel { SaveScreenViewModel(get()) }
    viewModel { GetScreenViewModel(get()) }
}

