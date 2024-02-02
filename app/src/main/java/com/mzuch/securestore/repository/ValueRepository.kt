package com.mzuch.securestore.repository

interface ValueRepository {
    fun getValue(key: String): String?
    fun saveValue(key: String, value: String): Boolean
}