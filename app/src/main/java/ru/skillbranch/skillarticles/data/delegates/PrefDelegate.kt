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
            is Boolean -> {thisRef.preferences.getBoolean(property.name, false); println(" type = Boolean")}
            is String -> {thisRef.preferences.getString(property.name, ""); println(" type = String")}
            is Long -> {thisRef.preferences.getLong(property.name, 0); println(" type = Long")}
            is Float -> {thisRef.preferences.getFloat(property.name, 0f); println(" type = Float")}
            is Int -> {thisRef.preferences.getInt(property.name, 0); println(" type = Int")}
            else ->    {null; println(" type = nothing")}
        }?.let{ it as T }
        println(" ret = $ret")
        return ret
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        println("set val = $value")
        this.value = value
    }
}