package ru.skillbranch.skillarticles.data.local

import android.content.Context
import androidx.preference.PreferenceManager
import ru.skillbranch.skillarticles.data.delegates.PrefDelegate

class PrefManager(context: Context) {

    val preferences  = PreferenceManager.getDefaultSharedPreferences(context)

//        val storedBoolean by PrefDelegate(false)
//        val storedString by PrefDelegate("")
//        val storedFloat by PrefDelegate(0f)
//        val storedInt by PrefDelegate(0)
//        val storedLong by PrefDelegate(0)


    fun clearAll() {
        preferences.all.clear()
    }
}