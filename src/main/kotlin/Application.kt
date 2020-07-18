import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import services.ClientApi
import services.endpoints.ChampionInfo
import services.endpoints.getChampionRotations

fun main() {
    ClientApi.championV3.getChampionRotations(object : Callback<ChampionInfo> {
        override fun onFailure(call: Call<ChampionInfo>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ChampionInfo>, response: Response<ChampionInfo>) {
            println(response)
        }
    })
    println("yolo")
}