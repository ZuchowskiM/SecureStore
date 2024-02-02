package com.mzuch.securestore.repository

import androidx.security.crypto.EncryptedSharedPreferences

class ValueRepositoryImpl(private val encryptedSharedPreferences: EncryptedSharedPreferences) :
    ValueRepository {

    override fun getValue(key: String): String? {
        return encryptedSharedPreferences.getString(key, null)
    }

    override fun setValue(key: String, value: String): Boolean {
        return encryptedSharedPreferences.edit().putString(key, value).commit()
    }
}