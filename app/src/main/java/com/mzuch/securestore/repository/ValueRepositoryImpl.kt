package com.mzuch.securestore.repository

import android.content.SharedPreferences

class ValueRepositoryImpl(private val encryptedSharedPreferences: SharedPreferences) :
    ValueRepository {

    override fun getValue(key: String): String? {
        return encryptedSharedPreferences.getString(key, null)
    }

    override fun saveValue(key: String, value: String): Boolean {
        return encryptedSharedPreferences.edit().putString(key, value).commit()
    }
}