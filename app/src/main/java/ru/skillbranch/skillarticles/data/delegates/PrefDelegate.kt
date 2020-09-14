package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) : ReadWriteProperty<PrefManager, T?> {

    private var value: T? = defaultValue

    override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
        return when(value){
            is Boolean -> thisRef.preferences.getBoolean(property.name,false)
            is String -> thisRef.preferences.getString(property.name,"")
            is Long -> thisRef.preferences.getLong(property.name,0)
            is Float -> thisRef.preferences.getFloat(property.name,0f)
            is Int -> thisRef.preferences.getInt(property.name,0)
            else -> null
        }?.let{ it as T }
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        this.value = value
    }
}