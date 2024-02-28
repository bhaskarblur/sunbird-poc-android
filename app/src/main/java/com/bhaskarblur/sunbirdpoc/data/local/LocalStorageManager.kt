package com.bhaskarblur.sunbirdpoc.data.local

import android.content.Context
import android.content.SharedPreferences

class LocalStorageManager(private val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    fun saveDid(did: String) {
        sharedPreferences.edit().putString("did", did).apply()
    }

    fun getDid(defaultValue: String): String {
        return sharedPreferences.getString("did", defaultValue) ?: defaultValue
    }

}
