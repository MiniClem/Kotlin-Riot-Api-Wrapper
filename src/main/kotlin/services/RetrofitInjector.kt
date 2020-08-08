package services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.interceptors.HeaderInterceptor

private val client: OkHttpClient = OkHttpClient.Builder().apply {
    interceptors().add(HeaderInterceptor(ClientApi.tokenProvider))
}.build()

class RetrofitInjector(private val route: String) {

    fun get(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://${route}")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}