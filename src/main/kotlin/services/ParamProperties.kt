package services

import services.interceptors.TokenProvider
import java.io.IOException
import java.util.*

class ParamProperties : Properties(), TokenProvider {

    init {
        val inputStream = ClassLoader.getSystemResourceAsStream("param.properties")
        if (inputStream != null)
            load(inputStream)
        else throw IOException("You must create a file named 'param.properties' containing at least the 'TOKEN' variable with your API token")
    }

    override fun getToken(): String {
        return getProperty("TOKEN").also { println(this) }
    }
}