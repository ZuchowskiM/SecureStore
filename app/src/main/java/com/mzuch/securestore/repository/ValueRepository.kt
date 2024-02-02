package com.mzuch.securestore.repository

interface ValueRepository {
    fun getValue(key: String): String?
    fun setValue(key: String, value: String): Boolean
}