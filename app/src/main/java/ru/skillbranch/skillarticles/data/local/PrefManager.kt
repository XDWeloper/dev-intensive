package ru.skillbranch.skillarticles.data.local

import android.content.Context
import androidx.preference.PreferenceManager
import ru.skillbranch.skillarticles.data.delegates.PrefDelegate

class PrefManager(context: Context) {

    val preferences  = PreferenceManager.getDefaultSharedPreferences(context)

//        var storedBoolean by PrefDelegate(false)
//        var storedString by PrefDelegate("")
//        var storedFloat by PrefDelegate(0f)
//        var storedInt by PrefDelegate(0)
//        var storedLong by PrefDelegate(0)
//
//    init {
//        val stor = storedBoolean
//        storedString = "test"
//        val sss = storedString
//        val i =0
//    }


    fun clearAll() {
        preferences.all.clear()
    }
}