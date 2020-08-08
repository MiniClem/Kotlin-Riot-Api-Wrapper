package services.endpoints

import retrofit2.Call
import retrofit2.http.GET

interface ChampionV3 {

    @GET("/lol/platform/v3/champion-rotations")
    fun getChampionRotations(): Call<ChampionInfo>
}

data class ChampionInfo(
    val maxNewPlayerLevel: Int,
    val freeChampionIdsForNewPlayers: List<Int>,
    val freeChampionIds: List<Int>
)