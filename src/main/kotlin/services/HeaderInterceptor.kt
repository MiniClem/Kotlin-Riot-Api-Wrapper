package services

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(interceptorInterface: InterceptorInterface) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Riot-Token", "hello")
                .build()
        )
    }
}

interface InterceptorInterface {
    fun getToken(): String
}