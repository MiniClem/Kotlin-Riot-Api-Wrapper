package services

import retrofit2.Call
import retrofit2.http.GET

interface LorRankedV1 {

    /**
     * Get the players in Master tier
     */
    @GET("/lor/ranked/v1/leaderboards")
    fun getLeaderboard(): Call<LeaderBoardDTO>
}

/**
 * @param players A list of players in Master tier.
 */
data class LeaderBoardDTO(
    val players: List<PlayerDTO>
)