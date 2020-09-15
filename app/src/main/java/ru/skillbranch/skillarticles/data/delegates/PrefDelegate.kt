package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) : ReadWriteProperty<PrefManager, T?> {

    private var value: T? = defaultValue

    override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
        val ret = when(value){
            is Boolean ->  thisRef.preferences.getBoolean(property.name, false)
            is String ->  thisRef.preferences.getString(property.name, "")
            is Long ->  thisRef.preferences.getLong(property.name, 0)
            is Float ->  thisRef.preferences.getFloat(property.name, 0f)
            is Int ->  thisRef.preferences.getInt(property.name, 0)
            else ->    null
        }?.let{ it as T }
        return ret
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        this.value = value
        when(value){
            is Boolean -> thisRef.preferences.edit().putBoolean(property.name, value)
            is String -> thisRef.preferences.edit().putString(property.name, value)
            is Long -> thisRef.preferences.edit().putLong(property.name, value)
            is Float -> thisRef.preferences.edit().putFloat(property.name, value)
            is Int -> thisRef.preferences.edit().putInt(property.name, value)
        }

    }
}