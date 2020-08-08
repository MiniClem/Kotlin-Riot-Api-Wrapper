package services.interceptors

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class BaseUrlInterceptor(
    @Volatile
    private var host: String
) : Interceptor {


    fun setHost(host: String) {
        this.host = HttpUrl.parse(host)!!.host()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .url(
                    request().url()
                        .newBuilder()
                        .host(host)
                        .build()
                )
                .build()
        )
    }
}