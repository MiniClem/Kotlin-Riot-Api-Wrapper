import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import services.*
import services.endpoints.ChampionInfo

fun main() {
    ClientApi.apply {
        tokenProvider = ParamProperties()
    }

    ClientApi.Services.championV3(PlatformRoutes.EUW1).getChampionRotations().enqueue(object : Callback<ChampionInfo> {
        override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
            println(response)
        }
    })
}