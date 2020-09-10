package ru.skillbranch.skillarticles.extensions

import kotlin.collections.listOf as listOf

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {

    val resList = mutableListOf<Int>()

    val posList = this
        ?.replace(substr, "***$$$~~~", ignoreCase)
        ?.split("***$$$~~~")
        ?.map { it.length }

    if (posList != null) {
        var currentPos = 0
        for ((index, len) in posList.withIndex()) {
            if (index != posList.size - 1) {
                currentPos += len
                resList.add(currentPos)
                currentPos += substr.length
            }
        }
    }

    return resList
}