package com.jkantech.dailyexpenses.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit


@SuppressLint("StaticFieldLeak")
object CustomDataStore {

    private lateinit var context: Context
    private lateinit var prefs:SharedPreferences
    //private lateinit var dataStore: DataStore<Preferences>

    fun init(contextr: Context){
        context =contextr
    }

    fun store(key:String, value: String){
        if (value!=null){
            prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
            prefs.edit {
                putString(key,value)
            }

        }else{
            Log.d("DATA","null")
        }



    }
    fun storeBoolean(key:String, value:Boolean?){
            prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
            prefs.edit {
                if (value != null) {
                    putBoolean (key, value)
                }
            }

    }

    fun restoreBoolean(key: String): Boolean {
        prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        return prefs.getBoolean(key,false)
    }
    fun restore(key: String): String? {
        prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        return prefs.getString(key, "")
    }

    fun restoreInt(key: String): Int {
        prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        return prefs.getInt(key, 0)
    }
    @SuppressLint("CommitPrefEdits")
    fun destroy(key: String) {
        prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
         prefs.edit().remove(key)
    }
}