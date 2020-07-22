import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import services.ClientApi
import services.ParamProperties
import services.PlatformRoutes
import services.RegionalRoutes
import services.endpoints.ChampionInfo

fun main() {
    ClientApi.apply {
//        setPlatformRoute(PlatformRoutes.EUW1)
//        setRegionalRoute(RegionalRoutes.EUROPE)
        setToken(ParamProperties())
    }
    ClientApi.championV3.getChampionRotationsSync().enqueue(object : Callback<ChampionInfo> {
        override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
            println(response)
        }
    })
}