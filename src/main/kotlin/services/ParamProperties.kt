package services

import java.io.File
import java.io.IOException
import java.util.*

class ParamProperties : Properties(), InterceptorInterface {

    init {
        val inputStream = ClassLoader.getSystemResourceAsStream("param.properties")
        if (inputStream != null)
            load(inputStream)
        else throw IOException("You must create a file named 'param.properties' containing at least the 'TOKEN' variable with your API token")
    }

    override fun getToken(): String = getProperty("TOKEN")
}