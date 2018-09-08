package com.creative.module

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson
import com.creative.domain.di_interfaces.AppContext
import com.creative.domain.di_interfaces.AppPreferenceName
import com.creative.domain.di_interfaces.ProjectScope

import javax.inject.Inject

@ProjectScope
class PreferenceModule @Inject
constructor(@AppContext context: Context, @AppPreferenceName prefName: String, private val gson: Gson) {
    private val preferences: SharedPreferences
    private val CUSTOMERPROFILE = "CUSTOMERPROFILE"

    var language: String?
        get() = this.getString("language", "ar")
        set(currentLanguage) = putString("language", currentLanguage!!)

    init {
        preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }


     fun putString(key: String, value: String?) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

//    fun saveUser(user:User_Entity){
//        putString("user",gson.toJson(user))
//    }
//    fun getUser():User_Entity?{
//        return gson.fromJson(getString("user",null),User_Entity::class.java)
//    }

     fun getString(key: String, defaultValue: String?): String? {
        return preferences.getString(key, defaultValue)
    }




}
