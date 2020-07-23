package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting
import java.util.function.BiConsumer
import javax.xml.transform.sax.SAXSource
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
                throw IllegalArgumentException("A user with this phone already exists")
            }else map[user.login] = user}

    fun registerUserBySalt(fullName: String?, email: String?, salt: String?, hash:String?, phone: String?
    ): User = User.makeUser(fullName!!, email = email, password = null, phone = phone, salt = salt, hash = hash)
        .also { user ->
            if(map.containsKey(user.login)) {
                map.remove(user.login)
                map.put(user.login, user)
            }else map[user.login] = user
        }


    fun loginUser(login: String, password: String) : String? {
        var _login  = login
        map.forEach{ it?.also {
            println(it.value.userInfo)
            println("password = ${it.value.accessCode}")
            println("login = ${it.key}")
            println("----------------------------------------")
        }}

        if (login.startsWith("+7")) {
            _login = login.replace("""[^+\d]""".toRegex(), "")
        }
        println("---------------------------------------------------")
        println("login = $_login")
        println("map.size = "+ map.size)
        println("password = "+ password)

        return map[_login.trim()]?.let {
            println("userInfo = ${it.userInfo}")
            if (it.checkPassword(password)) it.userInfo.also { println("reult = $it") }
            else null
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder(){
        map.clear()
    }

    fun requestAccessCode(login: String) {
        val _login = login?.replace("""[^+\d]""".toRegex(), "")
        map[_login].let {
            val newAccesseCode = it?.generateAccessCode()
            if (newAccesseCode != null) {
                it?.changePassword(it?.accessCode.toString(), newAccesseCode)
            }
        }
    }

    fun importUsers(list: List<String>): List<User>{
        var userList : MutableList<User> = mutableListOf()
        list?.forEach {
            val strBuff: List<String> = it?.split(";")
            val fullName: String? = strBuff.getOrNull(0)?.trim()
            var eMail: String? = null
            if(strBuff.getOrNull(1)?.trim()?.length != 0) {
                eMail = strBuff.getOrNull(1)?.trim()
                eMail = eMail?.toLowerCase()
            }
            val salt_hash : String? = strBuff.getOrNull(2)?.trim()
            var salt: String? = null
            var hash: String? = null
            if(salt_hash != null){
                salt = salt_hash.split(":").getOrNull(0)
                hash = salt_hash.split(":").getOrNull(1)
            }
            var phone: String? = null
            if(strBuff.get(3).length != 0) {
                phone = strBuff.getOrNull(3)
                phone = phone?.replace("""[^+\d]""".toRegex(), "")
            }

            registerUserBySalt(fullName,eMail,salt,hash,phone)
        }

        userList = map.values.toMutableList()
        return userList
    }
}























