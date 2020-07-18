package services.endpoints

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Headers

/*https://euw1.api.riotgames.com*/

interface ChampionV3 {
    @Headers(
        "X-Riot-Token: RGAPI-92c3deb2-2b8f-4634-92fc-03388771002f"
    )
    @GET("/lol/platform/v3/champion-rotations")
    fun getChampionRotationsSync(): Call<ChampionInfo>
}

fun ChampionV3.getChampionRotations(callback: Callback<ChampionInfo>) = getChampionRotationsSync().enqueue(callback)

data class ChampionInfo(
    val maxNewPlayerLevel: Int,
    val freeChampionIdsForNewPlayers: List<Int>,
    val freeChampionIds: List<Int>
)