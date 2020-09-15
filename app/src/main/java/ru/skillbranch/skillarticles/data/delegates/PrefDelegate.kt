package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) : ReadWriteProperty<PrefManager, T?> {

    private var value: T? = defaultValue

    override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
        return when(value){
            is Boolean ->  thisRef.preferences.getBoolean(property.name, value as Boolean)
            is String ->  thisRef.preferences.getString(property.name, value as String)
            is Long ->  thisRef.preferences.getLong(property.name, value as Long)
            is Float ->  thisRef.preferences.getFloat(property.name, value as Float)
            is Int ->  thisRef.preferences.getInt(property.name, value as Int)
            else ->    value
        }?.let{ it as T }
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        this.value = value
        when(value){
            is Boolean -> thisRef.preferences.edit().putBoolean(property.name, value as Boolean)
            is String -> thisRef.preferences.edit().putString(property.name, value as String)
            is Long -> thisRef.preferences.edit().putLong(property.name, value as Long)
            is Float -> thisRef.preferences.edit().putFloat(property.name, value as Float)
            is Int -> thisRef.preferences.edit().putInt(property.name, value as Int)
        }

    }
}