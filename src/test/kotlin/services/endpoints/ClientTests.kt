package services.endpoints

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
import kotlin.system.measureTimeMillis

class ClientTests {

    init {
        ClientApi.apply {
            tokenProvider = ParamProperties()
        }
    }

    @Test
    @Timeout(5000)
    fun `given a former url, when I switch route after launching some other before, then I got response from different routes`() =
        runBlocking {
            val firstCallTime = measureTimeMillis {
                // First, base url is pointing to the EUW1 platform
                ClientApi.Services.championV3(PlatformRoutes.EUW1).getChampionRotations()
                    .enqueue(object : Callback<ChampionInfo> {
                        override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
                            fail(t)
                        }

                        override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
//                            Assertions.assertTrue(response.isSuccessful)
                            Assertions.assertTrue(response.raw().toString().contains("euw1", ignoreCase = true))
                            println(response.raw())
                        }
                    })
            }

            // Switching platform to JP1

            // Base url should now point to the JP1 platform
            val secondCallTime = measureTimeMillis {
                ClientApi.Services.championV3(PlatformRoutes.JP1).getChampionRotations()
                    .enqueue(object : Callback<ChampionInfo> {
                        override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
                            fail(t)
                        }

                        override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
//                            Assertions.assertTrue(response.isSuccessful)
                            Assertions.assertTrue(response.raw().toString().contains("jp1", ignoreCase = true))
                            println(response.raw())
                        }

                    })
            }

            println("First call launched in $firstCallTime\n")
            println("Second call launched in $secondCallTime\n")

            delay(4000)
        }
}