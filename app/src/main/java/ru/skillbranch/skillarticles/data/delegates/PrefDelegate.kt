package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) {
    private var storedValue: T? = null

    operator fun provideDelegate(thisRef: PrefManager, prop: KProperty<*>): ReadWriteProperty<PrefManager, T?> {
        val key = prop.name
        return object : ReadWriteProperty<PrefManager, T?> {

            override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
                if (storedValue == null) {
                    @Suppress("UNCHECKED_CAST")
                    storedValue = when (defaultValue) {is Boolean -> thisRef.preferences.getBoolean(property.name,defaultValue as Boolean) as T
                        is String -> thisRef.preferences.getString(property.name,defaultValue as String) as T
                        is Long -> thisRef.preferences.getLong(property.name,defaultValue as Long) as T
                        is Float -> thisRef.preferences.getFloat(property.name,defaultValue as Float) as T
                        is Int -> thisRef.preferences.getInt(property.name,defaultValue as Int) as T
                        else -> error("This type can not be stored into Preferences")
                    }
                }
                return storedValue
            }

            override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
                with(thisRef.preferences.edit()) {
                    when (value) {
                        is Boolean -> putBoolean(key, value)
                        is String -> putString(key, value)
                        is Long -> putLong(key, value)
                        is Float -> putFloat(key, value)
                        is Int -> putInt(key, value)
                    }
                    apply()
                }
            }

        }
    }

}