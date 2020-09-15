package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) : ReadWriteProperty<PrefManager, T?> {

    //private var value: T? = defaultValue

    override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
        if(property.name.contains("Boolean")) return  thisRef.preferences.getBoolean(property.name, defaultValue as Boolean) as T
        if(property.name.contains("String")) return  thisRef.preferences.getString(property.name, defaultValue as String) as T
        if(property.name.contains("Long")) return  thisRef.preferences.getLong(property.name, defaultValue as Long) as T
        if(property.name.contains("Float")) return  thisRef.preferences.getFloat(property.name, defaultValue as Float) as T
        if(property.name.contains("Int")) return  thisRef.preferences.getInt(property.name, defaultValue as Int) as T
        return null
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        //this.value = value
        when(value){
            is Boolean -> thisRef.preferences.edit().putBoolean(property.name, value as Boolean).apply()
            is String -> thisRef.preferences.edit().putString(property.name, value as String).apply()
            is Long -> thisRef.preferences.edit().putLong(property.name, value as Long).apply()
            is Float -> thisRef.preferences.edit().putFloat(property.name, value as Float).apply()
            is Int -> thisRef.preferences.edit().putInt(property.name, value as Int).apply()
        }
    }
}