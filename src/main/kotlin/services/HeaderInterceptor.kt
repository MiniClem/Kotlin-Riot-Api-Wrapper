package services

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val interceptorInterface: InterceptorInterface) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
//                .addHeader("User-Agent", "Kotlin Riot Api Wrapper")
//                .addHeader("Accept-Language","en-US,en;q=0.7,fr;q=0.3")
//                .addHeader("Accept-Charset","application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("X-Riot-Token", interceptorInterface.getToken())
                .build()
        )
    }
}

interface InterceptorInterface {
    fun getToken(): String
}