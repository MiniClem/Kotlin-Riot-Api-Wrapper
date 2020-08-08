package services.endpoints

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.fail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import services.ClientApi
import services.ParamProperties
import services.PlatformRoutes
import services.RegionalRoutes

class ClientTests {

    init {
        ClientApi.apply {
            platformRoutes = PlatformRoutes.EUW1
            regionalRoutes = RegionalRoutes.EUROPE
            tokenProvider = ParamProperties()
        }
    }

    @Test
    @Timeout(5000)
    fun `test switch base url`() = runBlocking {
        // First, base url is pointing to the EUW1 platform
        ClientApi.Services.championV3.getChampionRotations().enqueue(object :  Callback<ChampionInfo> {
            override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
                Assertions.assertTrue(response.raw().toString().contains("euw1", ignoreCase = true))
                println(response.raw())
            }
        })

        // Switching platform to JP1
        ClientApi.platformRoutes = PlatformRoutes.JP1

        // Base url should now point to the JP1 platform
         ClientApi.Services.championV3.getChampionRotations().enqueue(object : Callback<ChampionInfo>{
             override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
                 fail(t)
             }

             override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
                 Assertions.assertTrue(response.raw().toString().contains("jp1", ignoreCase = true))
                 println(response.raw())
             }

         })

        delay(4000)
    }
}