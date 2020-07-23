package ru.skillbranch.kotlinexample.extensions

fun <T>List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T>{
val mutList = this.toMutableList()

   for (i in (mutList.size -1)  downTo 0){
       mutList.removeAt(i)
       if(predicate(this[i])) break
    }

    if(mutList.size == 0) return this
    else return mutList.toList()
}


