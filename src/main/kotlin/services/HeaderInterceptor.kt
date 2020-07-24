package services

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val interceptorInterface: InterceptorInterface) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 4.4; Nexus 5 Build/_BuildID_) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36")
                .addHeader("Accept-Language","en-US,en;q=0.7,fr;q=0.3")
                .addHeader("Accept-Charset","application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("X-Riot-Token", interceptorInterface.getToken())
//                .addHeader(    "\"Origin\"","\"https://github.com/\"")
                .build()
        )
    }
}

interface InterceptorInterface {
    fun getToken(): String
}