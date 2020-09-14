package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) : ReadWriteProperty<PrefManager, T?> {

    private var value: T? = defaultValue

    override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
        println("getValue $property.name")
        println("---------------------------")
        val ret = when(value){
            is Boolean -> {println(" type = Boolean"); thisRef.preferences.getBoolean(property.name, false) }
            is String -> {println(" type = String"); thisRef.preferences.getString(property.name, "") }
            is Long -> {println(" type = Long"); thisRef.preferences.getLong(property.name, 0) }
            is Float -> {println(" type = Float"); thisRef.preferences.getFloat(property.name, 0f) }
            is Int -> {println(" type = Int"); thisRef.preferences.getInt(property.name, 0) }
            else ->    {println(" type = nothing"); null }
        }?.let{ it as T }
        println(" ret = $ret")
        return ret
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        println("set val = $value")
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