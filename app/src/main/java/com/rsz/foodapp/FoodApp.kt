package com.rsz.foodapp

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import com.rsz.foodapp.network.HttpClient

class FoodApp : MultiDexApplication() {

    companion object {
        lateinit var instance: FoodApp

        fun getApp(): FoodApp {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token : String) {
        getPreferences().edit().putString("PREFERENCE_TOKEN", token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken() : String? {
        return getPreferences().getString("PREFERENCE_TOKEN", null)
    }

    fun setUser(user : String) {
        getPreferences().edit().putString("PREFERENCE_USER", user).apply()
        HttpClient.getInstance().buildRetrofitClient(user)
    }

    fun getUser() : String? {
        return getPreferences().getString("PREFERENCE_USER", null)
    }


}