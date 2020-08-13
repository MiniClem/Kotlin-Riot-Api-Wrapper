import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import services.ClientApi
import services.ParamProperties
import services.PlatformRoutes
import services.endpoints.ChampionInfo

fun main() {
    ClientApi.apply {
        // You must provide your API key,
        tokenProvider = ParamProperties()
    }
    // OR simply put
    /*ClientApi.apply {
        tokenProvider = object : TokenProvider {
            override fun getToken(): String {
                return "YOUR API KEY"
            }
        }
    }*/

    ClientApi.championV3(PlatformRoutes.EUW1).getChampionRotations().enqueue(object : Callback<ChampionInfo> {
        override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
            println(response)
        }
    })
}