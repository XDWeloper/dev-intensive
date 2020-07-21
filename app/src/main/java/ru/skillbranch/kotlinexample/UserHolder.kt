package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting
import kotlin.math.log
import ru.skillbranch.kotlinexample.UserHolder.map as map

object UserHolder {
    private val map = mutableMapOf<String,User>()

    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ): User = User.makeUser(fullName, email = email, password = password)
        .also { user ->
            if(map.containsKey(user.login)){
                throw IllegalArgumentException("A user with this email already exists")
            }else map[user.login] = user}

    fun registerUserByPhone(fullName: String, rawPhone: String
    ): User = User.makeUser(fullName, email = null, password = null, phone = rawPhone)
        .also { user ->
            if(map.containsKey(user.login)){
                throw IllegalArgumentException("A user with this email already exists")
            }else map[user.login] = user}


    fun loginUser(login: String, password: String) : String? {
        val _login = login?.replace("""[^+\d]""".toRegex(), "")
        return map[_login.trim()]?.let {
            if (it.checkPassword(password)) it.userInfo
            else null
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder(){
        map.clear()
    }

    fun requestAccessCode(login: String) {
        val _login = login?.replace("""[^+\d]""".toRegex(), "")
        val fullNmae = map[_login].also{ it.f }


    }
}